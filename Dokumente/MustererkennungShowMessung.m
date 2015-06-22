raw_data =dlmread("/home/jholscher/Downloads/2015.04.28/03/treppe3.csv" , ",");
TITEL = ["accelX (m/s^2)";"accelY (m/s^2)";"accelZ (m/s^2)";"gyroX (rad/s)";"gyroY (rad/s)";"gyroZ (rad/s)";"magnetX (uT)";"magnetY (uT)";"magnetZ (uT)"];

filt_data = raw_data(raw_data(:,1)== 1324189, :); 
timestamps = filt_data(:,2);
nr_values =  rows(filt_data);
figure(1)
for i = 1:9;
	subplot(3,3,i);
	hold on;
	plot( filt_data( : , i+2));
	line_mean = mean(filt_data( : , i+2));
	plot([0,nr_values], [line_mean, line_mean], "r");
	line_var = var(filt_data( : , i+2));
	plot([0,nr_values], [line_var, line_var], "g");
	xlabel(TITEL( i ,:))
	hold off;
endfor;