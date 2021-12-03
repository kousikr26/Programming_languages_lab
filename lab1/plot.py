import matplotlib.pyplot as plt
file1 = open("matrix_plot.txt","r")
x= list(map(int,list(file1.readline().strip().split(","))[:-1]))
x_new=[]
y_new=[]

y= list(map(float,list(file1.readline().strip().split(","))[:-1]))
for i in range(10):
    x_new.append(x[i])
    y_new.append(y[i])
for i in range(10,250,20):
    x_new.append(x[i])
    y_new.append(y[i])
file1.close()
plt.xlabel("# Threads")
plt.ylabel("Time(ms)")
plt.title("Plot of Time(ms) vs # Threads for Matrix Multiplication")
plt.plot(x_new,y_new,"o-")
plt.savefig("./matrix_plot.png")
plt.show()