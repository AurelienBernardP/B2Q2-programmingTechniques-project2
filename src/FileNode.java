public class FileNode extends NodeType{
    private final String content;

    public FileNode(String name, String content){
        super();
        this.content = content;
        this.name = name;
    }
    public String getContent(){
        return this.content;
    }

    public NodeType createAlias(){
        return new AliasNode((this.name)+"(alias)", this.content);
    }
    
    public NodeType createFile(String name, String content){
        return null;
    }

    public NodeType createFolder(String name){
        return null;
    }

    public NodeType createArchive(String name, String extension, int compressionLevel){
        return null;
    }

    public NodeType copyNode(){
        return new FileNode(new String(this.name+ "(copy)"), new String(this.content));
    }
    public String getInfo(int nbIdentation){
        return content;
    }
}
