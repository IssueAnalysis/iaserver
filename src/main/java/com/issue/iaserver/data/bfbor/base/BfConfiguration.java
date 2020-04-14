package com.issue.iaserver.data.bfbor.base;

import com.issue.iaserver.data.bfbor.exception.BfborException;

public class BfConfiguration {
    private String host;
    private int port;
    private int hashNumber;
    private double falsePositiveRate;
    private int estDataNumber;
    private int bitLength;
    /**
     * @param host redis主机ip
     * @param port redis端口
     * 直接指定Bit的长度
     * */
    public BfConfiguration(String host, int port, int bitLength) {
        this.host = host;
        this.port = port;
        this.bitLength = bitLength;
        this.hashNumber = 7;
        if (bitLength < 0){
            throw  new RuntimeException("用于去重的位信息长度不能为0");
        }
    }
    /**
     * @param host redis主机ip
     * @param port redis端口
     * @param hashNumber 需要使用的hash算法的数量,最多15个,最少1个
     * @param falsePositiveRate 误判率
     * @param estDataNumber 预估要判重的数量个数
     * 其中hashNumber,falsePositiveRate,estDataNumber会影响计算出的需要bit的个数（长度）
     * */
    public BfConfiguration(String host, int port, int hashNumber, double falsePositiveRate, int estDataNumber) {
        this.host = host;
        this.port = port;
        this.hashNumber = hashNumber;
        this.falsePositiveRate = falsePositiveRate;
        this.estDataNumber = estDataNumber;
        this.bitLength = computeBitLength(hashNumber, falsePositiveRate, estDataNumber);
    }
    private int computeBitLength(int hashNumber, double falsePositiveRate, int estDatanumber){
        if (hashNumber > 15 || hashNumber <0){
            throw new BfborException("哈希算法的数量应在 1~15 之间");
        }
        if (falsePositiveRate >=1 || falsePositiveRate <=0){
            throw new BfborException("重复率在 0~1 之间");
        }
        if (estDatanumber < 0){
            throw new BfborException("预估数据长度不能小于0");
        }
        double up = -1 * hashNumber * estDatanumber;
        double down = Math.log(1 - Math.pow(falsePositiveRate,1.0/hashNumber));
        int result = (int)(up / down);
        //System.out.println(result);
        return result;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getHashNumber() {
        return hashNumber;
    }

    public void setHashNumber(int hashNumber) {
        this.hashNumber = hashNumber;
    }

    public double getFalsePositiveRate() {
        return falsePositiveRate;
    }

    public void setFalsePositiveRate(double falsePositiveRate) {
        this.falsePositiveRate = falsePositiveRate;
    }

    public int getEstDataNumber() {
        return estDataNumber;
    }

    public void setEstDataNumber(int estDataNumber) {
        this.estDataNumber = estDataNumber;
    }

    public int getBitLength() {
        return bitLength;
    }

    public void setBitLength(int bitLength) {
        this.bitLength = bitLength;
    }

    @Override
    public String toString() {
        return "BfConfiguration{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", hashNumber=" + hashNumber +
                ", falsePositiveRate=" + falsePositiveRate +
                ", estDataNumber=" + estDataNumber +
                ", bitLength=" + bitLength +
                '}';
    }
}
