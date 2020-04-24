s = ""
counter = 0
while(counter < 250000):
    for i in range(80):
        s += 'N'
    counter += 80
    s += '\n'
    
with open("gunga.txt", "w+") as f:    
    f.write(s)