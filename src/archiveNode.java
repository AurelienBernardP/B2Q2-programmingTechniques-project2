
public class archiveNode extends nodeType{
    private final String name;
    private final String extension;
    private final int compressionLevel;

    public archiveNode(String name, String extension, int compressionLevel){
        this.name = name;
        this.extension = extension;
        this.compressionLevel = compressionLevel;
    }

    public nodeType createFile(String name, String content){
        //showPopupError("Cannot create a file inside an archive. \n");
        return null;
    }
    public void copyNode(){

    }
    public String getInfo(){
        return "info archive";
    }
    public  nodeType createFolder(String name){
    //    showPopupError("Cannot create a folder inside an archive. \n");
        return null;
    }

    public nodeType createAlias(){
        //showPopupError("Cannot create an alias inside an archive. \n");
        return null;
    }

    public nodeType createArchive(String name, String extension, int compressionLevel){
        //showPopupError("Cannot archive an archive. \n");
        return null;
    }

}