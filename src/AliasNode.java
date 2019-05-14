
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
        //showPopupError("Cannot create a copy of an alias. \n");
    }
    public NodeType copyNode(){
        return null;

    }
    public NodeType createFile(String name, String content){
        return null;
        //showPopupError("Cannot create a file inside an alias. \n");
    }

    public NodeType createFolder(String name){
        return null;
        //showPopupError("Cannot create a folder inside an alias. \n");
    }

    public NodeType createAlias(){
        return null;
        //showPopupError("Cannot create an alias inside an alias. \n");
    }

    public NodeType createArchive(String name, String extension, int compressionLevel){
        return null;
        //showPopupError("Cannot archive an alias. \n");
    }

    public String getInfo(int nbIdentation){
        return content;
    }

}