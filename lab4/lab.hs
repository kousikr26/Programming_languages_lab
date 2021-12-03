import qualified Data.List


main = do
    putStr "Enter comma separated integers : "
    line <- getLine
    let lis = read ("[" ++ line ++ "]") :: [Int]
    putStr "The LCM of the list is : "
    print (lcmArr lis)
    let tree = listToTree lis
    putStr "The Pre order traversal of the list is : "
    print (preorder tree)
    putStr "The Post order traversal of the list is : "
    print (postorder tree)
    putStr "The inorder traversal of the list is : "
    print (inorder tree)




lcmArr :: (Foldable t, Integral b) => t b -> b
lcmArr  inp=  foldr lcm 1 inp
        
       




data Tree x = Nil | Node (Tree x) x (Tree x) deriving Show

insert :: Ord x => Tree x -> x -> Tree x
insert Nil insertVal = Node Nil insertVal Nil
insert (Node l currentNodeVal r) insertVal
  | insertVal == currentNodeVal = Node l currentNodeVal r
  | insertVal < currentNodeVal = Node (insert l insertVal) currentNodeVal r
  | otherwise = Node l currentNodeVal (insert r insertVal)

listToTree :: Ord x => [x] -> Tree x
listToTree [] = Nil
listToTree (xs:x) = helper (Node Nil xs Nil) x
        where 
            helper y [] = y
            helper y (zs:z) = helper (insert y zs) z

inorder :: Tree x -> [x]
inorder Nil = []
inorder (Node l m r) = inorder l ++ [m] ++ inorder r

preorder :: Tree x -> [x]
preorder Nil = []
preorder (Node l m r) = [m] ++ preorder l ++ preorder r


postorder :: Tree x -> [x]
postorder Nil = []
postorder (Node l m r) = postorder l ++ postorder r ++ [m]

-- Test cases



-- Enter comma separated integers : 5,4,10,1,2,3
-- The LCM of the list is : 60
-- The Pre order traversal of the list is : [5,4,1,2,3,10]
-- The Post order traversal of the list is : [3,2,1,4,10,5]
-- The inorder traversal of the list is : [1,2,3,4,5,10]


-- Enter comma separated integers : 2,3,4,5,6,7,1,19
-- The LCM of the list is : 7980
-- The Pre order traversal of the list is : [2,1,3,4,5,6,7,19]
-- The Post order traversal of the list is : [1,19,7,6,5,4,3,2]
-- The inorder traversal of the list is : [1,2,3,4,5,6,7,19]

-- Enter comma separated integers : 10
-- The LCM of the list is : 10
-- The Pre order traversal of the list is : [10]
-- The Post order traversal of the list is : [10]
-- The inorder traversal of the list is : [10]

-- Enter comma separated integers : 10,19
-- The LCM of the list is : 190
-- The Pre order traversal of the list is : [10,19]
-- The Post order traversal of the list is : [19,10]
-- The inorder traversal of the list is : [10,19]


-- Enter comma separated integers : 69, 420, 888, 111, 73
-- The LCM of the list is : 52183320
-- The Pre order traversal of the list is : [69,420,111,73,888]
-- The Post order traversal of the list is : [73,111,888,420,69]
-- The inorder traversal of the list is : [69,73,111,420,888]

-- Enter comma separated integers : 87,54,12,123,1,5
-- The LCM of the list is : 642060
-- The Pre order traversal of the list is : [87,54,12,1,5,123]
-- The Post order traversal of the list is : [5,1,12,54,123,87]
-- The inorder traversal of the list is : [1,5,12,54,87,123]

-- Enter comma separated integers : 0
-- The LCM of the list is : 0
-- The Pre order traversal of the list is : [0]
-- The Post order traversal of the list is : [0]
-- The inorder traversal of the list is : [0]

-- Since BST only unique values appear

-- Enter comma separated integers : 34,34,34
-- The LCM of the list is : 34
-- The Pre order traversal of the list is : [34]
-- The Post order traversal of the list is : [34]
-- The inorder traversal of the list is : [34]


-- Enter comma separated integers : 23,87,1,3
-- The LCM of the list is : 2001
-- The Pre order traversal of the list is : [23,1,3,87]
-- The Post order traversal of the list is : [3,1,87,23]
-- The inorder traversal of the list is : [1,3,23,87]

-- Enter comma separated integers : 10,9,8,7,6,5,4,3,2,1
-- The LCM of the list is : 2520
-- The Pre order traversal of the list is : [10,9,8,7,6,5,4,3,2,1]
-- The Post order traversal of the list is : [1,2,3,4,5,6,7,8,9,10]
-- The inorder traversal of the list is : [1,2,3,4,5,6,7,8,9,10]
