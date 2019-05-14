public class Node{
    private nodeType content;
    private Node next;
    
    public Node(nodeType s){
        content = s;
        next = null;
    }
    
    public Node getNext(){
        return next;
    }
    
    public void setNext(Node n){
        next = n;
    }
    
    public nodeType getContent(){
        return content;
    }


    public void addNode(nodeType newNode){
        //Adding to the first element of the list
        Node newCell = new Node(newNode);
        newCell.setNext(next);
        this.setNext(newCell);

    }
}