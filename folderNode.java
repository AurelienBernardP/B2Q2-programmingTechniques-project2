import montefiore.ulg.ac.be.graphics.*;

public class folderNode extends nodeType{
    private String name;
    List list;

    public folderNode(String name){
        this.name = name;
    }

    public void createFile(){
        String[] propertiesFile = fileMenuDialog();
        if(propertiesFile == null){
            showPopup("Bitch nope.");
            return;
        }
        fileNode newFile = new fileNode(propertiesFile[0], propertiesFile[1]);
        list.addList(newFile);
    }
}