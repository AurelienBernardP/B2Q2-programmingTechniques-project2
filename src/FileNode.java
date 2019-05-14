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
        return new AliasNode(this.name, this.content);
    }
    
    public NodeType createFile(String name, String content){
        return null;
        //showPopupError("Cannot create a file inside a file. \n");
    }

    public NodeType createFolder(String name){
        return null;
        //showPopupError("Cannot create a folder inside a file. \n");
    }

    public NodeType createArchive(String name, String extension, int compressionLevel){
        return null;
        //showPopupError("Cannot archive a file. \n");
    }

    public NodeType copyNode(){
        return new FileNode(new String(this.name), new String(this.content));
        
    }
    public String getInfo(int nbIdentation){
        return content;
    }
}
