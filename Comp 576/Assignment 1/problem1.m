img = problemOne();

function img = problemOne()
    %do not change the name of the function
    img = zeros(128,128);
    for j = 1 : 128
        for k = 1:128
            if j > k
                img(j,k) = 1000;
            else
                img(j,k) = 0;
            end
        end
    end
    %return the image array
end
