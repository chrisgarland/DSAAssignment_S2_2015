import sun.misc.resources.Messages_zh_CN;

import java.util.NoSuchElementException;

/**
 * Created by chrisgarland on 13/10/15.
 */
public class BinarySearchTree
{
    private TreeNode root;
    private int currentHeight, totalHeight;

    public BinarySearchTree()
    {
        root = null;                                    //Start with empty tree
    }


    /**
     * Wrapper method for findRecursive() which traverses the
     * tree and tries to find a node given a key
     *
     * @param key - Given to findRecursive()
     * @return - value received by findRecursive()
     */
    public Carton[] find( String key )
    {
        return findRecursive( key, root );
    }


    /**
     * Recursive method for find().
     *
     * @param key         - Provided by user
     * @param currentNode - Starts as root
     * @return - value
     * @throws NoSuchElementException
     */
    private Carton[] findRecursive( String key, TreeNode currentNode ) throws NoSuchElementException
    {
        Carton[] value;

        if( currentNode == null )                                   //Base case: Not found
        {
            throw new NoSuchElementException( "Key: \"" + key + "\" not found" );
        }
        else if( key.equals( currentNode.key ) )                    //Base Case: Found
        {
            value = currentNode.container.getContainer();           //Array at node
        }
        else if( key.compareTo( currentNode.key ) < 0 )             //Key less than currentNode.key
        {
            value = findRecursive( key, currentNode.leftChild );    //recurse left
        }
        else                                                        //Key greater than currentNode.key
        {
            value = findRecursive( key, currentNode.rightChild );   //recurse right
        }

        return value;
    }


    /**
     * Wrapper method for insertRecursive() which inserts a
     * new node into binary search tree.
     *
     * @param key   - Provided by user
     * @param value - Provided by user
     */
    public void insert( String key, Carton value )
    {
        if( root == null )                                          //Empty tree
        {
            root = new TreeNode( key, value );
        }
        else                                                        //Not empty
        {
            insertRecursive( key, value, root );

        }
    }


    /**
     * Recursive method for insert()
     *
     * @param key         - Provided by user
     * @param value       - To insert
     * @param currentNode - Starts as root
     * @return - Update Node
     * @throws IllegalStateException
     */
    private TreeNode insertRecursive( String key, Carton value, TreeNode currentNode ) throws IllegalStateException
    {
        TreeNode updateNode = currentNode;

        if( currentNode == null )                                           //Base Case: Found
        {
            updateNode = new TreeNode( key, value );
        }
        else if( key.equals( currentNode.key ) )                            //Duplicate key
        {
            currentNode.container.insert( value );                          //Insert into container (array)
        }
        else if( key.compareTo( currentNode.key ) < 0 )                     //Key less than currentNode.key
        {
            currentNode.leftChild = insertRecursive( key, value, currentNode.leftChild );   //Recurse left
        }
        else if( key.compareTo( currentNode.key ) > 0 )                     //Key greater than currentNode.key
        {
            currentNode.rightChild = insertRecursive( key, value, currentNode.rightChild ); //Recurse right
        }

        return updateNode;
    }


    /**
     * Wrapper method for deleteRecursive() which deletes
     * a node from the binary search tree.
     *
     * @param key - Key of node to be deleted
     */
    public void delete( String key )
    {
        deleteRecursive( key, root );
    }


    /**
     * Recursive method for delete()
     *
     * @param key         - Provided by user
     * @param currentNode - Starts as root
     * @return - updateNode
     * @throws IllegalArgumentException
     */
    private TreeNode deleteRecursive( String key, TreeNode currentNode ) throws IllegalArgumentException
    {
        TreeNode updateNode = currentNode;

        if( currentNode == null )
        {
            throw new IllegalArgumentException( "Key not in tree" );
        }
        else if( key.equals( currentNode.key ) )                    //Base Case: Found
        {
            updateNode = deleteNode( currentNode );
        }
        else if( key.compareTo( currentNode.key ) < 0 )             //Key smaller than currNode.key
        {
            currentNode.leftChild = deleteRecursive( key, currentNode.leftChild );      //Recurse left
        }
        else if( key.compareTo( currentNode.key ) > 0 )              //Key greater than currNode.key
        {
            currentNode.rightChild = deleteRecursive( key, currentNode.rightChild );    //Recurse right
        }

        return updateNode;
    }


    /**
     * Called by deleteRecursive(). Does the actual deleting of node
     *
     * @param deleteNode - Node to be deleted
     * @return - update node
     */
    private TreeNode deleteNode( TreeNode deleteNode )
    {
        TreeNode updateNode;

        if( isLeaf( deleteNode ) )                                              //No children
        {
            updateNode = null;
        }
        else if( deleteNode.leftChild != null && deleteNode.rightChild == null )//One child - left
        {
            updateNode = deleteNode.leftChild;
        }
        else if( deleteNode.leftChild == null && deleteNode.rightChild != null )//One chile - right
        {
            updateNode = deleteNode.rightChild;
        }
        else                                                                    //Two children
        {
            updateNode = promoteSuccessor( deleteNode.rightChild );

            if( updateNode != deleteNode.rightChild )                           //No cycles
            {
                updateNode.rightChild = deleteNode.rightChild;
            }

            updateNode.leftChild = deleteNode.leftChild;
        }

        return updateNode;
    }


    /**
     * Called by deleteNode(). Promotes a leaf node
     *
     * @param currentNode - Provided by deleteNode()
     * @return - successor
     */
    private TreeNode promoteSuccessor( TreeNode currentNode )
    {
        TreeNode successor = currentNode;

        if( currentNode.leftChild != null )
        {
            successor = promoteSuccessor( currentNode.leftChild );

            if( successor == currentNode.leftChild )                            //Parent of successor
            {
                currentNode.leftChild = successor.rightChild;                   //Needs to adopt right child
            }
        }

        return successor;
    }


    /**
     * Wrapper method for heightRecursive() which finds
     * the height (number of levels) of binary tree.
     *
     * @return - (int) height of tree.
     */
    public int height()
    {
        currentHeight = 0;
        totalHeight = 0;

        return heightRecursive( root );                             //rootNode, currentHeight, totalHeight
    }


    /**
     * Recursive method called by height()
     *
     * @param currentNode - Starts as root
     * @return - (int) num of levels
     */
    private int heightRecursive( TreeNode currentNode )
    {

        if( currentNode != null )                                   //Base Case: Not Found
        {
            currentHeight++;

            if( currentHeight > totalHeight )
            {
                totalHeight = currentHeight;                        //Increment totalHeight when new depth is reached
            }

            heightRecursive( currentNode.leftChild );               //Recurse Left
            heightRecursive( currentNode.rightChild );              //Recurse Right

            currentHeight--;                                        //Decrement as we traverse back up the tree
        }
//      else
//      {
//          Base Case: Found (Not needed in code)
//      }

        return totalHeight;
    }


//Supporting Metods:

    /**
     * Checks to see whether a TreeNode is a leaf node.
     *
     * @param testNode - Node to be checked
     * @return - bool
     */
    private boolean isLeaf( TreeNode testNode )
    {
        return ( testNode.leftChild == null ) && ( testNode.rightChild == null );
    }


//Test Methods:

    /**
     * Wrapper method for printSubTree()
     *
     * Method used for testing purposes.
     * Prints the tree state.
     */
    public void printTree()
    {
        printSubTree( root, "" );
    }

    /**
     * Recursive method for printing tree. Called by
     * wrapper method.
     *
     * @param root -
     * @param indent -
     */
    private void printSubTree( TreeNode root, String indent )
    {
        if( root != null )
        {
            if( root.leftChild != null )
            {
                printSubTree( root.leftChild, indent + "      " );
                System.out.println( indent + "     /" );
            }

            System.out.println( indent + root.toString() );

            if( root.rightChild != null )
            {
                System.out.println( indent + "     \\" );
                printSubTree( root.rightChild, indent + "      " );
            }
        }
    }

//TreeNode:

    /**
     * Class made private as it is only used by BinarySearchTree{}.
     * This serves as an extra layer of abstraction.
     * <p/>
     * TreeNode{}: Each instance will be a node in the binary search tree.
     * Each node will contain a reference its own left child, right child and
     * to the actual stored value.
     */
    private class TreeNode
    {
        //All fields made public so visible to BinarySearchTree{}. This
        //eliminates the need for setters/getters
        public static final int MAX_CAPACITY = 1028;

        public String key;
        public CartonContainer container;
        public TreeNode leftChild;
        public TreeNode rightChild;


        /**
         * Alternate constructor asserts that the key is not null
         * before initialising all member fields.
         *
         * @param inKey - key
         * @param inVal - value
         * @throws IllegalArgumentException
         */
        public TreeNode( String inKey, Carton inVal ) throws IllegalArgumentException
        {
            if( inKey == null )
            {
                throw new IllegalArgumentException( "Key cannot be null" );
            }

            key = inKey;
            container = new CartonContainer();
            leftChild = null;
            rightChild = null;

            container.insert( inVal );
        }


        /**
         * Returns key as string
         *
         * @return - key as string
         */
    }
}
