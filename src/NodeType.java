

abstract class NodeType{
    protected String name;
    
    /**
     * Give access to the "name" variable of the instance
     * @return :name of the caller node as a string
     * 
     */
    public String getName(){
        return name;
    }
    /**
     * Set the "name" variable of the instance to the given "name"
     * @param name : String containing a name given to the node
     * 
     */
    public void setName(String name) {
		this.name = name;
	}
    /**
     * @param name : String containing the name of the new created file
     * @param content :content of the new file in String format
     * @return :null if file cannot be created from the current node, reference to the newly created file on success
     * 
     */
    public abstract NodeType createFile(String name, String content);

    /**
     * @param name : String containing the name of the new created file
     * @return :null if folder cannot be created from the current node, reference to the newly created folder on success
     * 
     */
    public abstract NodeType createFolder(String name);

    /**
     * @param name : String containing the name of the new created file
     * @param extension :Mock extension of the new archive given as a string
     * @param compressionLevel :Positive integer representing the compression level of the archive
     * @return :null if archive cannot be created from the current node, reference to the newly created archive on success
     * 
     */
    public abstract NodeType createArchive(String name, String extension, int compressionLevel);

    /**
     * @return :null if Alias of the current node cannot be created, reference to the newly created Alias on success
     * 
     */
    public abstract NodeType createAlias();

    /**
     * @param nbIndentation :Positive integer of spaces in the string before the first character of the string 
     * @return :String containing information about the caller node
     * 
     */
    public abstract String getInfo(int nbIdentation);

    /**
     * @return :null if a copy of the current node cannot be created, reference to the newly created copy on success
     * 
     */
    public abstract NodeType copyNode();
    
    @Override
    public String toString(){
        return name;
    }

}
