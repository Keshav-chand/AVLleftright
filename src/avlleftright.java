public class avlleftright{
    static class Node{
        int key, height;
        Node left,right;
    };
    static Node newnode(int data){
        Node temp=new Node();
        temp.key=data;
        temp.height=1;

        temp.left=null;
        temp.right=null;
        return temp;
    }
    public int getheight(Node N){
        if(N==null){
            return 0;
        }
        return N.height;
    }
    public int findmax(int a,int b){
        return(a>b)?a:b;
    }
    public Node rightrotation(Node y){
        Node x=y.left;//initializing variables at the position
        Node t2=x.right;
        
        x.right=y;//performing rotation
        y.left=t2;
        y.height = findmax(getheight(y.left), getheight(y.right)) + 1;
        x.height= findmax(getheight(x.left), getheight(x.right)) + 1;
        return x;//returning newnode
    }
    public Node leftrotation(Node x){
        Node y =x.right;//initializing variables at the position
        Node t2=y.left;

        y.left=x;//performing rotation
        x.right=t2;
        x.height= findmax(getheight(x.left), getheight(x.right)) + 1;
        y.height = findmax(getheight(y.left), getheight(y.right)) + 1;
        return y;//returning newnode
    }
    public int getbalance(Node N){
        if(N==null){
            return 0;
        }
        return getheight(N.left)-getheight(N.right);
    }
    public Node insert(Node node,int key){
        if(node==null){
            return (newnode(key));
        }
        if(key<node.key){
            node.left=insert(node.left,key);
        }
        else if(key>node.key){
            node.right=insert(node.right,key);
        }
        else{
            return node;//duplicate value not allowed
        }
        node.height=1+findmax(getheight(node.left),getheight(node.right));//used to update teh height of the node
        int balance=getbalance(node);

        //leftrightrotation
        if(balance> 1 && key>node.left.key){
            System.out.println("Value inserted successfully .. tree is unbalanced");
            System.out.println("Applying leftright rotation");
            node.left=leftrotation(node.left);
            return rightrotation(node);
        }
        return node;
    }
    static void inorder(Node root){
        if(root==null){
            return;
        }
        else{
            inorder(root.left);
            System.out.println(root.key + "{" + root.height + "}" + " ");
            inorder(root.right);
        }
    }
    public static void main(String[] args) {
        avlleftright e= new avlleftright();
        int value;
        Node root=null;
        int[]arr={ 42, 33, 55, 21, 39, 41};

        for(int i=0;i<arr.length;i++){
            value=arr[i];
        
        System.out.println("\nInserting " + value + " to tree");
        root=e.insert(root,value);
        System.out.println("\n Inorder binary tree");
        inorder(root);
        System.out.println();
    }
}
}