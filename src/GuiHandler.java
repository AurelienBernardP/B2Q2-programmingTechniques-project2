
import java.util.*;
import java.lang.*;
import java.io.*;
import montefiore.ulg.ac.be.graphics.*;

public class GuiHandler implements ExplorerEventsHandler {

	private ExplorerSwingView esv;
	private String log = "";
	private boolean printInFile;
	
    GuiHandler(String[] args) throws NullHandlerException {
        this.esv = new ExplorerSwingView(this);
        
		try {
        	// First step to do before anything !!! 
            this.esv.setRootNode(new FolderNode("Root")); // set the root node with a silly "A" object
        } catch (RootAlreadySetException e) {
            e.printStackTrace();
        }

		if(args.length != 0)
			if("log".equals(args[0]))
				  this.printInFile = true;

    }

	public void createAliasEvent(Object selectedNode) {
		NodeType newAlias;

		//Perform the requested manipulation on selectedNode and check if it is allowed
		if((newAlias = ((NodeType)selectedNode).createAlias()) != null){
			try {
				//Modify the JTree by adding the new node to the parent folder and check for exceptions
    	        FolderNode parent = (FolderNode)this.esv.addNodeToParentNode(newAlias);
				parent.addNodeInFolder(newAlias);

				//Update the logs
				String newLog = (new DateSystem(new UserSystem(new OsSystem(new CopyEvent(new EndLog()))))).log();
				this.log = this.log + (new String(newLog));
				
				esv.refreshTree();
			} catch (NoSelectedNodeException | NoParentNodeException e) {
				e.printStackTrace();
				esv.showPopupError("Error: creating alias.\n");
        	}
		}
		else{
			//Show an error popup window
			esv.showPopupError("Error: cannot create alias for this type of element.\n");
			return;
		}
	}

	public void createArchiveEvent(Object selectedNode) {

		//Get the user's inputs to create the archive
		String name = esv.displayArchiveWindow1();
		String extension = esv.displayArchiveWindow2();
		int compressionLVL = esv.displayArchiveWindow3();

		//Verify if all the data was successfully entered
		if(name == null || extension == null || compressionLVL < 0 || esv.isRootNodeSelected()){
			//If it is not, show an error popup window
			esv.showPopupError("Error: operation 'create archive' has been cancelled or the selected node is the root.\n");
			return;
		}

		//Perform the requested manipulation on selectedNode and check if it is allowed
		NodeType newArchive;
		if((newArchive = ((NodeType)selectedNode).createArchive(name, extension, compressionLVL)) != null){
			try {
				//Modify the JTree by adding the new node to the parent folder and check for exceptions				
    	        FolderNode parent = (FolderNode)this.esv.addNodeToParentNode(newArchive);
				parent.addNodeInFolder(newArchive);

				//Update the logs
				String newLog = (new DateSystem(new UserSystem(new OsSystem(new CopyEvent(new EndLog()))))).log();
				this.log = this.log + (new String(newLog));

				esv.refreshTree();
			} catch (NoSelectedNodeException | NoParentNodeException e) {
				e.printStackTrace();
				esv.showPopupError("Error: creating an archive.\n");
        	}
		}
		else{
			//if it is not, show an error popup window
			esv.showPopupError("Error: cannot create archive of this type of element.\n");
			return;
		}
	}

	private void createCopyFolderInJTree(Object selectedNode, int level){
		//Select the list of the selected folder
		Node currentNode = ((FolderNode)selectedNode).getContent();
		NodeType currentContent;

		//Browsing that list
		while(currentNode != null){
			//Adding the current element for the JTree component
			currentContent = currentNode.getContent();
			try{
				esv.addNodeToLastInsertedNode(currentContent, level);
			}
			catch(NoPreviousInsertedNodeException | LevelException e){
				e.printStackTrace();
				esv.showPopupError("Error copying.\n");
			}
			if(currentContent instanceof FolderNode){
				this.createCopyFolderInJTree(currentContent, level + 1);
			}

			currentNode = currentNode.getNext();
		}

	}

	public void createCopyEvent(Object selectedNode){
		//Verify that the selected node is not the root node
		if (esv.isRootNodeSelected()){
			//if it is report the error with a popup window
			esv.showPopupError("Error: cannot do a copy of the root.");
			return;
		}
		
		//Perform the requested manipulation on selectedNode and check if it is allowed
		NodeType newNode = ((NodeType)selectedNode).copyNode();
		if(newNode == null){
			//if it is not, show an error popup window
			esv.showPopupError("Error: copying element.\n");
			return;
		}
		try {
			//Modify the JTree component by adding the new node to the parent folder	
			((FolderNode)(this.esv.addNodeToParentNode(newNode))).addNodeInFolder(newNode);
			if (selectedNode instanceof FolderNode){
				this.createCopyFolderInJTree(selectedNode, 1);
			}

			//Update the logs
			String newLog = (new DateSystem(new UserSystem(new OsSystem(new CopyEvent(new EndLog()))))).log();
			this.log = this.log + (new String(newLog));

			esv.refreshTree();
		} catch (NoSelectedNodeException | NoParentNodeException e) {
			e.printStackTrace();
			esv.showPopupError("Error: copying element.\n");
		}
	}


	public void createFileEvent(Object selectedNode) {
		//Get the user's inputs to create the file
		String[] propertiesFile = this.esv.fileMenuDialog();

		//Verify if all the data was successfully entered
		if(propertiesFile == null && (propertiesFile[0] == null || propertiesFile[1] == null)){
			//if it is not, show an error popup window
			esv.showPopupError("Error: operation 'create file' has been cancelled. \n");
			return;
		}

		//Perform the requested manipulation on selectedNode and check if it is allowed
		NodeType newFile;
		if((newFile = ((NodeType)selectedNode).createFile(propertiesFile[0], propertiesFile[1])) != null){
			try {
				//Modify the JTree by adding the new node to the selected folder	
    	        this.esv.addNodeToSelectedNode(newFile);

				//Update the logs				
				String newLog = (new DateSystem(new UserSystem(new OsSystem(new CopyEvent(new EndLog()))))).log();
				this.log = this.log + (new String(newLog));

				esv.refreshTree();
			} catch (NoSelectedNodeException e) {
            	e.printStackTrace();
				esv.showPopupError("Error: creating file.\n");
        	}
		}
		else{
			//if it is not, show an error popup window
			esv.showPopupError("Error: cannot create file inside this type of element.\n");
			return;
		}
	}

	public void createFolderEvent(Object selectedNode) {
		//Get the user's input to create the folder
		String nameFolder = this.esv.folderMenuDialog();

		//Verify if all the data was successfully entered
		if(nameFolder == null){
			//if it is not, show an error popup window
			esv.showPopupError("Error: operation 'create folder' has been cancelled. \n");
			return;				
		}

		//Perform the requested manipulation on selectedNode and check if it is allowed
		NodeType newFolder;
		if((newFolder = ((NodeType)selectedNode).createFolder(nameFolder)) != null){
			try {
				//Modify the JTree by adding the new node to the selected folder
				this.esv.addNodeToSelectedNode(newFolder);

				//Update the logs				
				String newLog = (new DateSystem(new UserSystem(new OsSystem(new CopyEvent(new EndLog()))))).log();
				this.log = this.log + (new String(newLog));

				esv.refreshTree();
			} catch (NoSelectedNodeException e) {
            	e.printStackTrace();
				esv.showPopupError("Error: creating folder.\n");
        	}
		}
		else{
			//if it is not, show an error popup window
			esv.showPopupError("Error: cannot create folder inside this type of element.\n");
			return;
		}
	}

	public void doubleClickEvent(Object selectedNode) {
		//Get text area manager, clear the text area and add the information about the selected node
		TextAreaManager jt = this.esv.getTextAreaManager();
		jt.clearAllText();
		jt.appendText(((NodeType)selectedNode).getInfo(0));

		//Update the logs		
		String newLog = (new DateSystem(new UserSystem(new OsSystem(new CopyEvent(new EndLog()))))).log();
		this.log = this.log + (new String(newLog));
	}

	public void eventExit() {

		if(printInFile == true){
			//Print the complete events log in the file "log.txt"
			try {
				File file = new File("log.txt");
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write("Final logs:\n" + log);
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				esv.showPopupError("Error: cannot create a new file for the log.\n");
			}
		}
		else{
			//Print the complete events log in the terminal
			System.out.println("Final logs:");
			System.out.println(log);
		}
	}
}
