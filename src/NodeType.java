

abstract class NodeType{
    protected String name;
    
    /**
     * Give access to the "name" variable of the instance
     * @return :name of the instance as a string
     * 
     */
    public String getName(){
        return name;
    }
    /**
     * Set the "name" variable of the instance to the given "name"
     * @param name : String containing a name given to the instance
     * 
     */
    public void setName(String name) {
		this.name = name;
    }

    /**
     * Give access to the "name" variable of the instance
     * @return :name of the instance as a string
     * 
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * If allowed, create a file inside the instance 
     * 
     * @param name : String containing the name of the new file
     * @param content :content of the new file in String format
     * @return :null if file cannot be created from the current instance,
     *          FileNode, reference to the newly created file on success
     * 
     */
    public abstract NodeType createFile(String name, String content);

    /**
     * If allowed, create a folder inside the instance 
     * @param name : String containing the name of the new folder
     * @return :null if folder cannot be created from the current instance,
     *          FolderNode, reference to the newly created folder on success
     * 
     */
    public abstract NodeType createFolder(String name);

    /**
     * If allowed, create an archive inside the instance 
     * @param name : String containing the name of the new archive
     * @param extension :Mock extension of the new archive given as a string
     * @param compressionLevel :Positive integer representing the compression level of the archive
     * @return :null if archive cannot be created from the current instance, 
     *          ArchiveNode, reference to the newly created archive on success
     * 
     */
    public abstract NodeType createArchive(String name, String extension, int compressionLevel);

    /**
     * If allowed, create an alias of the instance 
     * @return :null if an alias of the current instance cannot be created, 
     *          AliasNode, reference to the newly created Alias on success
     * 
     */
    public abstract NodeType createAlias();

    /**
     * Give access to the content of the instance
     * @param nbIndentation :Positive integer of spaces in the string before the first character of the string 
     * @return :String containing information about the instance
     * 
     */
    public abstract String getInfo(int nbIdentation);

    /**
     * If allowed, create a copy of the instance
     * @return :null if a copy of the current instance cannot be created, 
     *          NodeType, reference to the newly created copy on success
     * 
     */
    public abstract NodeType copyNode();

}
