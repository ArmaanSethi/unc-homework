function [ mag_out, phase_out ] = AmpPhaseDFT( inputimage )
    X = inputimage;
    [nRow, nCol, nDim] = size( X );
    
    if nDim ~= 1
        error('input image is not grayscale')        
    end
    if nRow ~= 128 || nCol ~= 128
        warning('input image is not 128x128')        
        X = imresize( X, [128, 128] );
        nRow = 128;
        nCol = 128;
    end
    if ~isa(X,'double')
        warning('input image is not double precision')
        X = im2double( X );
    end
    Y = fft2( X );
    Yreal = real( Y );
    Yimag = imag( Y );
    th = 1e-10;
    mag = 2*abs( Y );
    phase = -1*angle(Y);
    
    idx =  abs(Yreal) < th & ~(abs(Yimag) <th) ;
    phase(idx) = pi/2;
    
    idx =  abs(Yimag) < th ;
    phase(idx) = 0;

    
    mag(1,1) = Y(1,1);
    mag(1,nCol/2+1) = Y(1,nCol/2+1);
    mag(nRow/2+1,1) = Y(nRow/2+1,1);
    mag(nRow/2+1, nCol/2+1) = Y(nRow/2+1, nCol/2+1);
    
    mag_out = mag(1:65,:);
    phase_out = phase(1:65,:);
end