% HW 3, problem 2

function I = problemTwo()
    
    mat = zeros(65,128);
    sigma = 2;
    
    for j=1:65
        kx=(j-1)/128;
        for i=1:65
            ky=(i-1)/128;
            mat(i,j)=(kx^2+ky^2)*exp(-.5*((2*pi*sigma)^2)*(kx^2))*exp(-.5*((2*pi*sigma)^2)*(ky^2));
        end
    end
    
    for j=66:128
        kx=(j-129)/128;
        for i=1:65
            ky=(i-1)/128;
            mat(i,j)=(kx^2+ky^2)*exp(-.5*((2*pi*sigma)^2)*(kx^2))*exp(-.5*((2*pi*sigma)^2)*(ky^2));
        end
    end
    
    I = mat(:,1:65);
    
end
