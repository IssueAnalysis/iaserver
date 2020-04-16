package com.issue.iaserver.webserver.common;


public class BusinessException extends Exception {
    private CommonError commonError;

    public BusinessException(ErrorType errorType){
        super();
        this.commonError = new CommonError(errorType);
    }

    public BusinessException(ErrorType errorType, String errMsg){
        super();
        this.commonError = new CommonError(errorType);
        this.commonError.setErrMsg(errMsg);
    }

    public CommonError getCommonError() {
        return commonError;
    }

    public void setCommonError(CommonError commonError) {
        this.commonError = commonError;
    }
}
