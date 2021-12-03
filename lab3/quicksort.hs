-- quicksort function
quicksort :: Ord a => [a] -> [a]
quicksort [] = []
quicksort (pivot:x) = quicksort xl ++ [pivot] ++ quicksort xr
                        where
                            xl = filter (<= pivot) x
                            xr = filter (> pivot) x

-- main function to print test cases
main = do
    print "Test case #0 : quicksort [3,2,1] : "
    print (quicksort [3,2,1])
    print "Test case #1 : quicksort [3,2,4,5,1] : "
    print (quicksort [3, 2, 4, 5,1])
    print "Test case #2 : quicksort [3,2,1,1,1,2,3] : "
    print (quicksort [3, 2, 1,1,1,2,3])
    print "Test case #3 : quicksort [10,20,1,4,5,6] : "
    print (quicksort [10,20,1,4,5,6])
    print "Test case #4 : quicksort [\"oranges\",\"avocados\",\"apples\"] : "
    print (quicksort ["oranges","avocados","apples"])
    print "Test case #5 : quicksort [3.14,2.11,2.01,3.11] : "
    print (quicksort [3.14, 2.11, 2.01, 3.11])
    
       