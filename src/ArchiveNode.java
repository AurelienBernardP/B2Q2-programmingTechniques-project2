
public class ArchiveNode extends NodeType{
    private final String extension;
    private final String info;
    private final int compressionLevel;

    public ArchiveNode(String name, String extension, int compressionLevel, String content){
        super.setName(name);
        this.extension = extension;
        this.compressionLevel = compressionLevel;
        this.info = content;
    }

    public NodeType createFile(String name, String content){
        return null;
    }
    public NodeType copyNode(){
        return new ArchiveNode(new String(super.name + "(copy)"), new String(extension),compressionLevel ,new String(this.info));

    }
    public String getInfo(int nbIdentation){
        return this.info;
    }
    public  NodeType createFolder(String name){
        return null;
    }

    public NodeType createAlias(){
        return null;
    }

    public NodeType createArchive(String name, String extension, int compressionLevel){
        return null;
    }

}