% HW 3, problem 4

function [I1A, I1B, I2A, I2B] = problemFour(img1, img2)
    mat1 = zeros(65,128);
    mat2 = zeros(65,128);
    sigma = 2;
    
    for j=1:65
        kx=(j-1)/128;
        for i=1:65
            ky=(i-1)/128;
            mat1(i,j)=(kx^2+ky^2)*exp(-.5*((2*pi*sigma)^2)*(kx^2))*exp(-.5*((2*pi*sigma)^2)*(ky^2));
        end
    end
    
    for j=66:128
        kx=(j-129)/128;
        for i=1:65
            ky=(i-1)/128;
            mat1(i,j)=(kx^2+ky^2)*exp(-.5*((2*pi*sigma)^2)*(kx^2))*exp(-.5*((2*pi*sigma)^2)*(ky^2));
        end
    end
    
    [mag1,phase1] = AmpPhaseDFT(img1);
    [mag2,phase2] = AmpPhaseDFT(img2);
    
    mag1A = mag1.*mat1;
    mag2A = mag2.*mat1;
    
    sigma = 5;
    
    for j=1:65
        kx=(j-1)/128;
        for i=1:65
            ky=(i-1)/128;
            mat2(i,j)=(kx^2+ky^2)*exp(-.5*((2*pi*sigma)^2)*(kx^2))*exp(-.5*((2*pi*sigma)^2)*(ky^2));
        end
    end
    
    for j=66:128
        kx=(j-129)/128;
        for i=1:65
            ky=(i-1)/128;
            mat2(i,j)=(kx^2+ky^2)*exp(-.5*((2*pi*sigma)^2)*(kx^2))*exp(-.5*((2*pi*sigma)^2)*(ky^2));
        end
    end
    
    mag1B = mag1.*mat2;
    mag2B = mag2.*mat2;
    % code from part 3 ends
    
    I1A = ReconfromAmpPhase(mag1A,phase1);
    I1B = ReconfromAmpPhase(mag1B,phase1);
    
    I2A = ReconfromAmpPhase(mag2A,phase2);
    I2B = ReconfromAmpPhase(mag2B,phase2);
end
