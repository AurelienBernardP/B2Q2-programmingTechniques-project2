public class FolderNode extends NodeType{
    private Node content;

    public FolderNode(String name){
        super();
        this.name = name;
    }


    /**
     * Give access to the first cell of the list "content"
     * @return :reference to the first node to a chained list of nodes contained in the folder.
     * 
     */
    public Node getContent(){
        return this.content;
    }

    /**
     * Add "newNode" in the list representing the content of the folder.
     * @param newNode : reference to the node to be inserted in "content" of the instance.
     */
    public void addNodeInFolder(NodeType newNode){
        if(newNode != null){
            //Check if the list is empty or not
            if(content == null){
                this.content = new Node(newNode);
            }else{
                this.content.addNode(newNode);
            }
        }
    }

    public NodeType createFile(String name, String content){
        FileNode newFile = new FileNode(name, content);
        addNodeInFolder(newFile);
        return newFile;
    }

    public NodeType createFolder(String name){
        FolderNode newFolder = new FolderNode(name);
        addNodeInFolder(newFolder);
        return newFolder;
        
    }

    public NodeType copyNode(){
        //Create a new empty Folder
        FolderNode newFolder = new FolderNode(new String(super.name + "(copy)"));

        //Copying each element of the list into the new folder.
        Node currentNode = content;
        NodeType currentContent;
        while(currentNode != null){
            currentContent = currentNode.getContent();
            if(currentContent instanceof AliasNode){
                newFolder.addNodeInFolder(new AliasNode(currentContent.getName(), ((AliasNode)currentContent).getInfo(0)));
            }
            else{
                newFolder.addNodeInFolder(currentContent.copyNode());
            }
            currentNode = currentNode.getNext();
        }
        return (NodeType) newFolder;
    }

    public NodeType createArchive(String name, String extension, int compressionLevel){
        NodeType newArchive = new ArchiveNode(name+extension, extension, compressionLevel, new String(this.getInfo(0)));
        return newArchive;
    }

    public NodeType createAlias(){
        return null;
    }
    
    public String getInfo(int nbIndentation){
        //For each element of the list, the content is saved in the string "folderContent"
        String folderContent = "";
        Node currentNode = content;
        NodeType currentContent;
        while(currentNode != null){
            currentContent = currentNode.getContent();

            //Based on the given "nbIndentation", add indentation to the string
            for(int i = nbIndentation; i > 0; i--)
                folderContent = folderContent + "    ";
            if(nbIndentation != 0)
                folderContent = folderContent + "-";

            //Checks if the current element is a folder.
            if(currentContent instanceof FolderNode){
                //If it is, a recursive call is needed
                folderContent = folderContent + " " + currentContent.getName() + "\n";
                folderContent = folderContent + currentContent.getInfo(nbIndentation + 1);
            }
            else{
                folderContent = folderContent + " " + currentContent.getName() + "\n";
            }
            currentNode = currentNode.getNext();
        }
        return folderContent;
    }
}