
public class aliasNode extends nodeType{
    private final String content;
    private final String name;

    public aliasNode(String name, String content){
        this.content = content;
        this.name = name;
    }
    public String getContent(){
        return this.content;
    }
    
    public nodeType createCopy(){
        return null;
        //showPopupError("Cannot create a copy of an alias. \n");
    }
    public void copyNode(){

    }
    public nodeType createFile(String name){
        return null;
        //showPopupError("Cannot create a file inside an alias. \n");
    }

    public nodeType createFolder(String name){
        return null;
        //showPopupError("Cannot create a folder inside an alias. \n");
    }

    public nodeType createAlias(){
        return null;
        //showPopupError("Cannot create an alias inside an alias. \n");
    }

    public nodeType createArchive(String name, String extension, int compressionLevel){
        return null;
        //showPopupError("Cannot archive an alias. \n");
    }

    public String getInfo(){
        return "yas alias";
    }

}