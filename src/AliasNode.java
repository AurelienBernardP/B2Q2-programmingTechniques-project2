
public class AliasNode extends NodeType{
    private final String content;

    public AliasNode(String name, String content){
        super();
        this.name = name;
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    
    public NodeType createCopy(){
        return null;
    }
    public NodeType copyNode(){
        return null;

    }
    public NodeType createFile(String name, String content){
        return null;
    }

    public NodeType createFolder(String name){
        return null;
    }

    public NodeType createAlias(){
        return null;
    }

    public NodeType createArchive(String name, String extension, int compressionLevel){
        return null;
    }

    public String getInfo(int nbIdentation){
        return content;
    }

}