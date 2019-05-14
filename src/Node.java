public class Node{
    private NodeType content;
    private Node next;
    
    public Node(NodeType s){
        content = s;
        next = null;
    }
    
    public Node getNext(){
        return next;
    }
    
    public void setNext(Node n){
        next = n;
    }
    
    public NodeType getContent(){
        return content;
    }


    public void addNode(NodeType newNode){
        //Adding to the first element of the list
        Node newCell = new Node(newNode);
        newCell.setNext(next);
        this.setNext(newCell);

    }

}