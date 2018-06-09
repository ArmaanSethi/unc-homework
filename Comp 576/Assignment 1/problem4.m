%Problem 4
[v, res, comp] = problemFour();
v;
res;
comp;

function [v,res,comp] = problemFour()
    %do not change the name of the function
    
    %Problem 1
    img = zeros(128,128);
    for j=1:128
        for k=1:128
            if j>k
                img(j,k)=1000;
            end
        end
    end

    %Problem 2
    img_x = zeros(128,128);
    img_y = zeros(128,128);
    for j=2:127
        for k=2:127
            img_x(j,k) = (img(j,k-1)-img(j,k+1))/2;
            img_y(j,k) = (img(j+1,k)-img(j-1,k))/2;
        end
    end
    
    %Problem 3
    u = [1,1];
    u = u/norm(u);
    res1 = zeros(128,128);
    for j=1:128
        for k=1:128
            res1(j,k) = dot(u,[img_x(j,k); img_y(j,k)]);
        end
    end
        
    %Problem 4
    v = [sin(pi/6), cos(pi/6)]; 
    
    res2 = zeros(128,128); %variable we're checking for
    for j=1:128
        for k=1:128
            res2(j,k) = dot(v,[img_x(j,k); img_y(j,k)]);
        end
    end
    
    res = [res2(2,2), res2(2,3), res2(2,4)];
    %disp(res);
    
    comp = ['<', '=', '='];
    
    %return v 
    %return res 
end


