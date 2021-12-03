
-- babylonian method to calculate square root
rec :: (Ord t, Fractional t) => t -> t -> t -> t
rec x y n 
        | x - y < 0.000001 = x
        | otherwise = rec ((x+y)/2) (2*n/(x+y)) n

-- utility function
squareRoot :: (Ord t, Fractional t) => t -> t
squareRoot n = rec n 1 n

-- main function to print test cases
main = do
    print "Test case #0 : squareRoot 23.56 : "
    print (squareRoot 23.56)
    print "Test case #1 : squareRoot [3,2,4,5,1] : "
    print (squareRoot 5)
    print "Test case #2 : squareRoot 5 : "
    print (squareRoot 256)
    print "Test case #3 : squareRoot 1.24 : "
    print (squareRoot 1.24)
    print "Test case #4 : squareRoot 4.9 : "
    print (squareRoot 4.9)
    print "Test case #5 : squareRoot 102.536 : "
    print (squareRoot 102.536)