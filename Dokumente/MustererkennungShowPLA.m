addpath('/home/jholscher/jsonlab');
files_gehen = ["/home/jholscher/git/Mustererkennung/LernDaten/02/gehen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/07/gehen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/08/gehen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/13/gehen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/16/gehen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/17/gehen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/20/gehen.json"];


files_sitzen = ["/home/jholscher/git/Mustererkennung/LernDaten/02/sitzen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/07/sitzen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/08/sitzen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/13/sitzen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/16/sitzen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/17/sitzen.json";
	    "/home/jholscher/git/Mustererkennung/LernDaten/20/sitzen.json"];
	    
files_A = files_gehen(:,:);
files_B = files_sitzen(:,:);
for i = 1 : 7;
	sensor(i,1,:) = loadjson(files_A(i,:)).sensor0.x0x30_1324189;
	sensorG(i,1,:) = loadjson(files_B(i,:)).sensor0.x0x30_1324189;
	endfor;
maximal = 51.5208;
minimal = 1;
figure(1);
	for i = 1 : 5;
		for a = 1 : length(sensor{i,1,10}.rangeAccXWithDelta);
			plot(sensor{i,1,10}.rangeAccXWithDelta(a),0.8, "k", "marker", "*", "markersize", 10, "linewidth", 1);
			hold on;
		endfor;
		for a = 1 : length(sensorG{i,1,10}.rangeAccXWithDelta);
			plot(sensorG{i,1,10}.rangeAccXWithDelta(a),0.2, "r", "marker", "+",  "markersize", 10,"linewidth", 1);
			hold on;
		endfor;		
	endfor;
	x=linspace(minimal, maximal,100);
	o = 1./(1+ exp(-2.5*(-0.5*26.380474268493472 + (19.845690673655035.*(((x./maximal))+1)./2)))); 
	plot(x,o);
	hold off;
title('RangeACCX0')
    