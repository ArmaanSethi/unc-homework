function [ I, L, R ] = hw5(image, n, m)
    
    I = mat2gray(image);
    
    k=2^(n-m); % number of boxes
    box_length = 2^m;
    
    controlVals = zeros(k,k);
    numBoxes = 2^(n-m);
    
    for i=1:k
        for j=1:k
            for q=1:box_length
                for r=1:box_length
                    controlVals(i,j) = controlVals(i,j) + I(q+(i-1)*box_length,r+(j-1)*box_length);
                end
            end
            controlVals(i,j) = controlVals(i,j)/(box_length^2);
        end
    end
    
    
    dt = 2^(-m);
    mat = [-1 3 -3 1; 3 -6 3 0; -3 0 3 0; 1 4 1 0];
    
    
    for row = 1:k
        % Handle first group of control points
        tValsFirst = -1.5+dt:dt:1;  % (-1.5 to 1) * width
        I_mat = [controlVals(row,1); controlVals(row,2); controlVals(row,3); controlVals(row,4)];
        m2 = mat*I_mat;
        outputSlot=1;
        for i = 1:size(tValsFirst,2)
            t = tValsFirst(i);
            kMat = [t^3 t^2 t 1];
            rowSpline(row,outputSlot) = (1/6)*kMat*m2;
            outputSlot=outputSlot+1;
        end
        
        % Handle the middle groups of control points
        tValsMid = dt:dt:1;                         % (0 to 1) * width
        for k2 = 3:numBoxes-3
            % Handle this particular middle group
            I_mat = [controlVals(row,k2-1); controlVals(row,k2); controlVals(row,k2+1); controlVals(row,k2+2)];
            m2 = mat*I_mat;
            for i = 1:size(tValsMid,2)
                t = tValsMid(i);
                kMat = [t^3 t^2 t 1];
                rowSpline(row,outputSlot) = (1/6)*kMat*m2;
                outputSlot=outputSlot+1;
            end
        end
        
        % Handle the last group of control points
        tValsLast = dt:dt:2.5;                 % (0 to 2.5) * width
        I_mat = [controlVals(row,numBoxes-3); controlVals(row,numBoxes-2); controlVals(row,numBoxes-1); controlVals(row,numBoxes)];
        m2 = mat*I_mat;
        for i = 1:size(tValsLast,2)
            t = tValsLast(i);
            kMat = [t^3 t^2 t 1];
            rowSpline(row,outputSlot) = (1/6)*kMat*m2;
            outputSlot=outputSlot+1;
        end
    end
    
    % control values are now rowSplines
    for col = 1:2^n
        % Handle first group of control points
        tValsFirst = -1.5+dt:dt:1;  % (-1.5 to 1) * width
        I_mat = [rowSpline(1,col); rowSpline(2,col); rowSpline(3,col); rowSpline(4,col)];
        m2 = mat*I_mat;
        outputSlot=1;
        for i = 1:size(tValsFirst,2)
            t = tValsFirst(i);
            kMat = [t^3 t^2 t 1];
            colSpline(outputSlot,col) = (1/6)*kMat*m2;
            outputSlot=outputSlot+1;
        end
        
        % Handle the middle groups of control points
        tValsMid = dt:dt:1;                         % (0 to 1) * width
        for k2 = 3:numBoxes-3
            % Handle this particular middle group
            I_mat = [rowSpline(k2-1,col); rowSpline(k2,col); rowSpline(k2+1,col); rowSpline(k2+2,col)];
            m2 = mat*I_mat;
            for i = 1:size(tValsMid,2)
                t = tValsMid(i);
                kMat = [t^3 t^2 t 1];
                colSpline(outputSlot,col) = (1/6)*kMat*m2;
                outputSlot=outputSlot+1;
            end
        end
        
        % Handle the last group of control points
        tValsLast = dt:dt:2.5;                 % (0 to 2.5) * width
        I_mat = [rowSpline(numBoxes-3,col); rowSpline(numBoxes-2,col); rowSpline(numBoxes-1,col); rowSpline(numBoxes,col)];
        m2 = mat*I_mat;
        for i = 1:size(tValsLast,2)
            t = tValsLast(i);
            kMat = [t^3 t^2 t 1];
            colSpline(outputSlot,col) = (1/6)*kMat*m2;
            outputSlot=outputSlot+1;
        end
    end
    
    
    L = mat2gray(colSpline);
    R = I./L;
    
    figure
    imshow(I)
    title('I')
    figure
    imshow(L)
    title('L')
    figure
    imshow(R)
    title('R')
    
end
