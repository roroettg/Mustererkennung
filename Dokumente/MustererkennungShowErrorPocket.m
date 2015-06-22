raw_data =dlmread("/home/jholscher/workspace/Mustererkennung2/Errors_Pocket.txt" , ",");
pla =dlmread("/home/jholscher/workspace/Mustererkennung2/Errors_PLA.txt" , ",");
plot(pla(:,1), "r")
hold on;
plot(raw_data(:,1), "b")
title("Fehler")
hold off;