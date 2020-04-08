这是接口文件夹，需要用的接口都放在这里

- UserService
    - UserDO login(long id, String password);
    - long addUser(UserDO userDO);
    - UserDO getOne(long id);
    
- OperateFileService
    - void uploadFile(UserDO user, String filePath);
    - Document getOneFile(String url);
    - ArrayList<Document> getFileContent(String url);
    - ArrayList<Document> getFileByUser(String url, long userid);
    - ArrayList<Document> getFileByText(String url, String content);