function [im1, im2, im1_sampled, im2_sampled] = problemFour(n, k)
        
    fX = k/n;  % x frequency
    
    x = 1:n;
    xy = repelem(x,n,1);
    
    im1 = sin(2*pi*fX.*xy);
    
    % figure
    % imshow((im1))
    
    im2 = sin(2*pi*(fX+1).*xy);
    % figure
    % imshow(im2)
    
    n_sampled = floor(n/4);
    
    im1_sampled = zeros(n_sampled,n_sampled);
    im2_sampled = zeros(n_sampled,n_sampled);
    
    
    for i=1:n_sampled
        i1=1; j1=1;
        for j=1:n_sampled
            im1_sampled(i,j) = sum(sum(im1(i1:i1+3,j1:j1+3)))/16;
            im2_sampled(i,j) = sum(sum(im2(i1:i1+3,j1:j1+3)))/16;
            i1=i1+4;
            j1=j1+4;
        end
    end

    
    % figure
    % imshow(im1_sampled)
    % figure
    % imshow(im2_sampled)
    

end
