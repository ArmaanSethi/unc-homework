% HW 3, problem 5

function [I1A, I1B, I2A, I2B] = problemFive(img1, img2)
    
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
    
    img1A = ReconfromAmpPhase(mag1A,phase1);
    img1B = ReconfromAmpPhase(mag1B,phase1);
    
    img2A = ReconfromAmpPhase(mag2A,phase2);
    img2B = ReconfromAmpPhase(mag2B,phase2);
    
    I1A = zeros(128,128);
    I1B = zeros(128,128);
    I2A = zeros(128,128);
    I2B = zeros(128,128);
        
    for j=1:128
        for i=1:128
            
            if (abs(img1A(i,j))<10e-7)
                I1A(i,j)=0;                
            elseif ((i>1) && (abs(abs(img1A(i,j))+abs(img1A(i-1,j))-abs(img1A(i,j)+img1A(i-1,j)))>10e-7))
                I1A(i,j)=1000;
            elseif ((i<128) && (abs(abs(img1A(i,j))+abs(img1A(i+1,j))-abs(img1A(i,j)+img1A(i+1,j)))>10e-7))
                I1A(i,j)=1000;
            elseif ((j>1) && (abs(abs(img1A(i,j))+abs(img1A(i,j-1))-abs(img1A(i,j)+img1A(i,j-1)))>10e-7))
                I1A(i,j)=1000;
            elseif ((j<128) && (abs(abs(img1A(i,j))+abs(img1A(i,j+1))-abs(img1A(i,j)+img1A(i,j+1)))>10e-7))
                I1A(i,j)=1000;
            elseif ((i>1) && (j>1) && (abs(abs(img1A(i,j))+abs(img1A(i-1,j-1))-abs(img1A(i,j)+img1A(i-1,j-1)))>10e-7))
                I1A(i,j)=1000;
            elseif ((i>1) && (j<128) && (abs(abs(img1A(i,j))+abs(img1A(i-1,j+1))-abs(img1A(i,j)+img1A(i-1,j+1)))>10e-7))
                I1A(i,j)=1000;
            elseif ((i<128) && (j>1) && (abs(abs(img1A(i,j))+abs(img1A(i+1,j-1))-abs(img1A(i,j)+img1A(i+1,j-1)))>10e-7))
                I1A(i,j)=1000;
            elseif ((i<128) && (j<128) && (abs(abs(img1A(i,j))+abs(img1A(i+1,j+1))-abs(img1A(i,j)+img1A(i+1,j+1)))>10e-7))
                I1A(i,j)=1000;
            else
                I1A(i,j)=0;
            end
            
            if (abs(img1B(i,j))<10e-7)
                I1B(i,j)=0;                
            elseif ((i>1) && (abs(abs(img1B(i,j))+abs(img1B(i-1,j))-abs(img1B(i,j)+img1B(i-1,j)))>10e-7))
                I1B(i,j)=1000;
            elseif ((i<128) && (abs(abs(img1B(i,j))+abs(img1B(i+1,j))-abs(img1B(i,j)+img1B(i+1,j)))>10e-7))
                I1B(i,j)=1000;
            elseif ((j>1) && (abs(abs(img1B(i,j))+abs(img1B(i,j-1))-abs(img1B(i,j)+img1B(i,j-1)))>10e-7))
                I1B(i,j)=1000;
            elseif ((j<128) && (abs(abs(img1B(i,j))+abs(img1B(i,j+1))-abs(img1B(i,j)+img1B(i,j+1)))>10e-7))
                I1B(i,j)=1000;
            elseif ((i>1) && (j>1) && (abs(abs(img1B(i,j))+abs(img1B(i-1,j-1))-abs(img1B(i,j)+img1B(i-1,j-1)))>10e-7))
                I1B(i,j)=1000;
            elseif ((i>1) && (j<128) && (abs(abs(img1B(i,j))+abs(img1B(i-1,j+1))-abs(img1B(i,j)+img1B(i-1,j+1)))>10e-7))
                I1B(i,j)=1000;
            elseif ((i<128) && (j>1) && (abs(abs(img1B(i,j))+abs(img1B(i+1,j-1))-abs(img1B(i,j)+img1B(i+1,j-1)))>10e-7))
                I1B(i,j)=1000;
            elseif ((i<128) && (j<128) && (abs(abs(img1B(i,j))+abs(img1B(i+1,j+1))-abs(img1B(i,j)+img1B(i+1,j+1)))>10e-7))
                I1B(i,j)=1000;
            else
                I1B(i,j)=0;
            end
            
            if (abs(img2A(i,j))<10e-7)
                I2A(i,j)=0;                
            elseif ((i>1) && (abs(abs(img2A(i,j))+abs(img2A(i-1,j))-abs(img2A(i,j)+img2A(i-1,j)))>10e-7))
                I2A(i,j)=1000;
            elseif ((i<128) && (abs(abs(img2A(i,j))+abs(img2A(i+1,j))-abs(img2A(i,j)+img2A(i+1,j)))>10e-7))
                I2A(i,j)=1000;
            elseif ((j>1) && (abs(abs(img2A(i,j))+abs(img2A(i,j-1))-abs(img2A(i,j)+img2A(i,j-1)))>10e-7))
                I2A(i,j)=1000;
            elseif ((j<128) && (abs(abs(img2A(i,j))+abs(img2A(i,j+1))-abs(img2A(i,j)+img2A(i,j+1)))>10e-7))
                I2A(i,j)=1000;
            elseif ((i>1) && (j>1) && (abs(abs(img2A(i,j))+abs(img2A(i-1,j-1))-abs(img2A(i,j)+img2A(i-1,j-1)))>10e-7))
                I2A(i,j)=1000;
            elseif ((i>1) && (j<128) && (abs(abs(img2A(i,j))+abs(img2A(i-1,j+1))-abs(img2A(i,j)+img2A(i-1,j+1)))>10e-7))
                I2A(i,j)=1000;
            elseif ((i<128) && (j>1) && (abs(abs(img2A(i,j))+abs(img2A(i+1,j-1))-abs(img2A(i,j)+img2A(i+1,j-1)))>10e-7))
                I2A(i,j)=1000;
            elseif ((i<128) && (j<128) && (abs(abs(img2A(i,j))+abs(img2A(i+1,j+1))-abs(img2A(i,j)+img2A(i+1,j+1)))>10e-7))
                I2A(i,j)=1000;
            else
                I2A(i,j)=0;
            end
            
            if (abs(img2B(i,j))<10e-7)
                I2B(i,j)=0;                
            elseif ((i>1) && (abs(abs(img2B(i,j))+abs(img2B(i-1,j))-abs(img2B(i,j)+img2B(i-1,j)))>10e-7))
                I2B(i,j)=1000;
            elseif ((i<128) && (abs(abs(img2B(i,j))+abs(img2B(i+1,j))-abs(img2B(i,j)+img2B(i+1,j)))>10e-7))
                I2B(i,j)=1000;
            elseif ((j>1) && (abs(abs(img2B(i,j))+abs(img2B(i,j-1))-abs(img2B(i,j)+img2B(i,j-1)))>10e-7))
                I2B(i,j)=1000;
            elseif ((j<128) && (abs(abs(img2B(i,j))+abs(img2B(i,j+1))-abs(img2B(i,j)+img2B(i,j+1)))>10e-7))
                I2B(i,j)=1000;
            elseif ((i>1) && (j>1) && (abs(abs(img2B(i,j))+abs(img2B(i-1,j-1))-abs(img2B(i,j)+img2B(i-1,j-1)))>10e-7))
                I2B(i,j)=1000;
            elseif ((i>1) && (j<128) && (abs(abs(img2B(i,j))+abs(img2B(i-1,j+1))-abs(img2B(i,j)+img2B(i-1,j+1)))>10e-7))
                I2B(i,j)=1000;
            elseif ((i<128) && (j>1) && (abs(abs(img2B(i,j))+abs(img2B(i+1,j-1))-abs(img2B(i,j)+img2B(i+1,j-1)))>10e-7))
                I2B(i,j)=1000;
            elseif ((i<128) && (j<128) && (abs(abs(img2B(i,j))+abs(img2B(i+1,j+1))-abs(img2B(i,j)+img2B(i+1,j+1)))>10e-7))
                I2B(i,j)=1000;
            else
                I2B(i,j)=0;
            end
            
        end
    end
    
    
end
