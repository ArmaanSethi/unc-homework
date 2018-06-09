function [outputimage]= ReconfromAmpPhase(mag,phase)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
N = 128;
scale = N^2;
xvec = 1:N;
yvec = (1:N)';
x_mtx = repmat( xvec, N, 1 );
y_mtx = repmat( yvec, 1, N );
xy_mtx = repmat( xvec, N, 1 ) + repmat( yvec, 1, N );

outputimage = mag(1,1)*ones( N, N )+...
    mag(1,N/2+1)*cos( pi*(x_mtx-1) )+...
    mag(N/2+1,1)*cos( pi*(y_mtx-1) )+...
    mag(N/2+1,N/2+1)*cos( pi*(xy_mtx-2) );
outputimage = outputimage / scale;

for k2 = 2:N/2
    for k1 = N/2+2:N
        outputimage = outputimage + mag(k2,k1)*cos( 2*pi*( (k1-1)*(x_mtx-1)+(k2-1)*(y_mtx-1))/N - phase(k2,k1) )/(scale) ;
    end
end

for k2 = 1:N/2+1
    for k1 = 2:N/2
        outputimage = outputimage + mag(k2,k1)*cos( 2*pi*( (k1-1)*(x_mtx-1)+(k2-1)*(y_mtx-1))/N - phase(k2,k1) )/(scale) ;
    end
end

for k2 = 2:N/2
    k1 = 1;
    outputimage = outputimage + mag(k2,k1)*cos( 2*pi*( (k1-1)*(x_mtx-1)+(k2-1)*(y_mtx-1))/N - phase(k2,k1) )/(scale) ;
    k1 = N/2+1;
    outputimage = outputimage + mag(k2,k1)*cos( 2*pi*( (k1-1)*(x_mtx-1)+(k2-1)*(y_mtx-1))/N - phase(k2,k1) )/(scale) ;
end

end

