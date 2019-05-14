

abstract class nodeType{
    private String name;


    public String getName(){
        return this.name;
    }

    public abstract nodeType createFile(String name, String content);
    public abstract nodeType createFolder(String name);
    public abstract nodeType createArchive(String name, String extension, int compressionLevel);
    public abstract nodeType createAlias();
    public abstract String getInfo();
    public abstract void copyNode();

}
