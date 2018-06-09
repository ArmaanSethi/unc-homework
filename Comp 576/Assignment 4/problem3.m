function [face1, face2, face3,face4, rmse1, rmse2, rmse3, rmse4] = problemThree(im_array, index1, index2, index3, index4)
    im_array2 = im2double(im_array); 
    num_training_images = 60;
    num_basis_vectors = 40;
    testimage = 1;
    
    images = im_array2(1:num_training_images,:); %choose 48 images
    
    A = images; %create A
    At = A.'; %Create At
    C = A*At; %int's are too small, must cast as double
   
    [V,D] = eigs(C, size(C,1)); %eigenvalue/eigenvector of AAt
    V_ata = At * V; %eigenvectors of AtA
    V_ata = V_ata.';
    
    eigenface = normr(V_ata(1:num_basis_vectors,:)); %choose first 8 vectors and normalize
    
    eigenface = normr(eigenface);
    c1 = zeros(num_basis_vectors);
    c2 = zeros(num_basis_vectors);
    c3 = zeros(num_basis_vectors);
    c4 = zeros(num_basis_vectors);
    
    for i = 1:num_basis_vectors
        c1(i) = dot(im_array2(index1,:), eigenface(i, :), 2);
        c2(i) = dot(im_array2(index2,:), eigenface(i, :), 2);
        c3(i) = dot(im_array2(index3,:), eigenface(i, :), 2);
        c4(i) = dot(im_array2(index4,:), eigenface(i, :), 2);
    end
    
    face1 = zeros(1,16384);
    face2 = zeros(1,16384);
    face3 = zeros(1,16384);
    face4 = zeros(1,16384);
    
    for i=1:num_basis_vectors
        face1(1,:) = face1(1,:) + c1(i).*eigenface(i,:);
        face2(1,:) = face2(1,:) + c2(i).*eigenface(i,:);
        face3(1,:) = face3(1,:) + c3(i).*eigenface(i,:);
        face4(1,:) = face4(1,:) + c4(i).*eigenface(i,:);
    end
    
    face1 = mat2gray(face1);
    face2 = mat2gray(face2);
    face3 = mat2gray(face3);
    face4 = mat2gray(face4);
        
    rmse1 = sqrt(mean((face1(1,:) - mat2gray(im_array2(index1,:))).^2));  % Root Mean Squared Error
    rmse2 = sqrt(mean((face2(1,:) - mat2gray(im_array2(index2,:))).^2));  % Root Mean Squared Error
    rmse3 = sqrt(mean((face3(1,:) - mat2gray(im_array2(index3,:))).^2));  % Root Mean Squared Error
    rmse4 = sqrt(mean((face4(1,:) - mat2gray(im_array2(index4,:))).^2));  % Root Mean Squared Error

end
