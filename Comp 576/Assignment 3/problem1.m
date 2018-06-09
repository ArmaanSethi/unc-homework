function [IA, IB, x_start, y_start] = problemOne(img, x_i, y_i)
    
    x_start = x_i;
    y_start = y_i;
    
    IA = img(y_i:y_i+127,x_i:x_i+127);
    
    IB = zeros(128,128);
    for j=1:128
        for k=1:128
            if (j==k)
                IB(j,k)=500;
            elseif (j>k)
                IB(j,k)=1000;
            end
        end
    end
    
end

