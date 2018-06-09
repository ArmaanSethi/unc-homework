img = zeros(128,128);
%do not change the name of the function
for j = 1 : 128
    for k = 1:128
        if j > k
            img(j,k) = 1000;
        else
            img(j,k) = 0;
        end
    end
end


%do not change the name of the function
img_x = zeros(128, 128);
img_y = zeros(128, 128);
for j = 2:127
    for k = 2:127
        img_x(j, k) = (img(j, k-1) - img(j, k+1))/2;
        img_y(j, k) = (img(j+1, k) - img(j-1, k))/2;
    end
end
%return two image arrays
img_x
img_y

