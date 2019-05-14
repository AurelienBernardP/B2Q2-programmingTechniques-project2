
import java.util.*;
import java.lang.*;
import java.io.*;

import montefiore.ulg.ac.be.graphics.*;

public class GuiHandler implements ExplorerEventsHandler {

	private ExplorerSwingView esv;
	
    GuiHandler(String[] args) throws NullHandlerException {
        this.esv = new ExplorerSwingView(this);
        
        try {
        	// First step to do before anything !!! 
            this.esv.setRootNode(new folderNode("Root")); // set the root node with a silly "A" object
        } catch (RootAlreadySetException e) {
            e.printStackTrace();
        }
    }
	
	public void createAliasEvent(Object selectedNode) {
		nodeType newAlias;
		if((newAlias = selectedNode.createAlias()) != null){
			try {
    	        nodeType parent = (nodeType)this.esv.addNodeToParentNode(newAlias);
				parent.addNodeInFolder(newAlias);
				esv.refreshTree();
			} catch (NoSelectedNodeException e) {
				e.printStackTrace();
				esv.showPopupError("Error adding node\n");
        	} catch(NoParentNodeException p){
				p.printStackTrace();
				esv.showPopupError("error, this node has no parent\n");
			}
		}
	}

	public void createArchiveEvent(Object selectedNode) {
		String name = esv.displayArchiveWindow1();
		String extension = esv.displayArchiveWindow2();
		int compressionLVL = esv.displayArchiveWindow3();
		if(name == null || extension == null || compressionLVL < 0 || esv.isRootNodeSelected()){
			esv.showPopupError("Operation 'create archive' has been cancelled. or selected ode is root\n");
			return;
		}

		nodeType newArchive;
		if((newArchive = ((nodeType)selectedNode).createArchive(name, extension, compressionLVL)) != null){
			try {
    	        nodeType parent = (nodeType)this.esv.addNodeToParentNode(newArchive);
				parent.addNodeInFolder(newArchive);
				esv.refreshTree();
			} catch (NoSelectedNodeException e) {
				e.printStackTrace();
				esv.showPopupError("Error adding node\n");
        	} catch(NoParentNodeException p){
				p.printStackTrace();
				esv.showPopupError("error, this node has no parent\n");
			}
		}
	}

	public void createCopyEvent(Object selectedNode) {
		// TODO Auto-generated method stub
	}

	public void createFileEvent(Object selectedNode) {
		String[] propertiesFile = this.esv.fileMenuDialog();
		if(propertiesFile[0] == null || propertiesFile[1] == null){
			esv.showPopupError("Operation 'create file' has been cancelled. \n");
			return;
		}

		nodeType newFile;
		if((newFile = ((nodeType)selectedNode).createFile(propertiesFile[0], propertiesFile[1])) != null){
			try {
    	        this.esv.addNodeToSelectedNode(newFile);
				esv.refreshTree();
			} catch (NoSelectedNodeException e) {
            	e.printStackTrace();
				esv.showPopupError("Error adding node\n");
        	}
		}
		//Si détails error pas nécessaire go with an else queen
	}

	public void createFolderEvent(Object selectedNode) {
		String nameFolder = this.esv.folderMenuDialog();
		if(nameFolder == null){
			esv.showPopupError("Operation 'create folder' has been cancelled. \n");
			return;				
		}

		nodeType newFolder;
		if((newFolder = ((nodeType)selectedNode).createFolder(nameFolder)) != null){
			try {
    	        this.esv.addNodeToSelectedNode(newFolder);
				esv.refreshTree();
			} catch (NoSelectedNodeException e) {
            	e.printStackTrace();
				esv.showPopupError("Error adding node\n");
        	}
		}
	}

	public void doubleClickEvent(Object selectedNode) {
		TextAreaManager jt = this.esv.getTextAreaManager();
		jt.clearAllText();
		jt.appendText(((nodeType)selectedNode).getInfo());
	}

	public void eventExit() {
		// TODO Auto-generated method stub
	}
}
