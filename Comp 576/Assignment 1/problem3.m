[u, res] = problemThree();

function [u,res] = problemThree()
    %do not change the name of the function
    img = zeros(128,128);
    for j=1:128
        for k=1:128
            if j>k
                img(j,k)=1000;
            end
        end
    end
    
    img_x = zeros(128,128);
    img_y = zeros(128,128);
    for j=2:127
        for k=2:127
            img_x(j,k) = (img(j,k-1)-img(j,k+1))/2;
            img_y(j,k) = (img(j+1,k)-img(j-1,k))/2;
        end
    end

    
    u = [1,1];
    u = u/norm(u);

    res1 = zeros(128,128);
    for j=1:128
        for k=1:128
            res1(j,k) = dot(u,[img_x(j,k); img_y(j,k)]);
        end
    end
    
    %%res = [res1(1,1), res1(1,2), res1(1,3)];
    
    res = res1;

    %return u 
    %return res 
end


