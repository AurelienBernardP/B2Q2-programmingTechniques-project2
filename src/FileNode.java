public class FileNode extends nodeType{
    private final String content;

    public FileNode(String name, String content){
        super();
        this.content = content;
        this.name = name;
    }
    public String getContent(){
        return this.content;
    }

    public nodeType createAlias(){
        return new aliasNode(this.name, this.content);
    }
    
    public nodeType createFile(String name, String content){
        return null;
        //showPopupError("Cannot create a file inside a file. \n");
    }

    public nodeType createFolder(String name){
        return null;
        //showPopupError("Cannot create a folder inside a file. \n");
    }

    public nodeType createArchive(String name, String extension, int compressionLevel){
        return null;
        //showPopupError("Cannot archive a file. \n");
    }

    public nodeType copyNode(){
        return new FileNode(new String(this.name), new String(this.content));
        
    }
    public String getInfo(int nbIdentation){
        return content;
    }
}
