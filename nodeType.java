

abstract class nodeType{
    private String name;


    public String getName(){
        return this.name;
    }

    public abstract void createFile();
    public abstract void createFolder();
    //public abstract void createArchive();
    //public abstract void createAlias();

    public abstract void copyNode();

}
