
public class ArchiveNode extends NodeType{
    private final String extension;
    private final int compressionLevel;

    public ArchiveNode(String name, String extension, int compressionLevel){
        super.setName(name);
        this.extension = extension;
        this.compressionLevel = compressionLevel;
    }

    public NodeType createFile(String name, String content){
        //showPopupError("Cannot create a file inside an archive. \n");
        return null;
    }
    public NodeType copyNode(){
        return new ArchiveNode(new String(super.name + "(copy)"), new String(extension), compressionLevel);

    }
    public String getInfo(int nbIdentation){
        return "Name: "+ name + "\n" + "Extension: "+ extension + "\n" + "Compression Level: " + compressionLevel;
    }
    public  NodeType createFolder(String name){
    //    showPopupError("Cannot create a folder inside an archive. \n");
        return null;
    }

    public NodeType createAlias(){
        //showPopupError("Cannot create an alias inside an archive. \n");
        return null;
    }

    public NodeType createArchive(String name, String extension, int compressionLevel){
        //showPopupError("Cannot archive an archive. \n");
        return null;
    }

}