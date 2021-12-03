-- List to store calculated fibonacci values recursively (and lazily)
fibs :: [Integer]
fibs = 0 : 1 : zipWith (+) fibs (tail fibs)

-- fibonacci function to get from list
fibonacci :: Int  -> Integer 
fibonacci n = fibs !! n

-- Main function to run test cases
main = do
  print "Test case #0 : fibonacci 5 : "
  print (fibonacci 5)
  print "Test case #1 : fibonacci 10 : "
  print (fibonacci 10)
  print "Test case #2 : fibonacci 15 : "
  print (fibonacci 15)
  print "Test case #3 : fibonacci 50 : "
  print (fibonacci 50)
  print "Test case #4 : fibonacci 200 : "
  print (fibonacci 200)
  print "Test case #5 : fibonacci 300 : "
  print (fibonacci 300)