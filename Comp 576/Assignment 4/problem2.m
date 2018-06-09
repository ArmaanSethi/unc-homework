function [face0, face1, morphed_face] = problemTwo(im_array, index1, index2)
    im_array2 = im2double(im_array); 
    num_training_images = 60;
    num_basis_vectors = 40;
    testimage = 1;
    
    images = im_array2(1:num_training_images,:); %choose 48 images
    
    A = images; %create A
    At = A.'; %Create At
    C = A*At; %int's are too small, must cast as double
   
    [V,D] = eigs(C,60); %eigenvalue/eigenvector of AAt
    V_ata = At * V; %eigenvectors of AtA
    V_ata = V_ata.';
    
    eigenface = normr(V_ata(1:num_basis_vectors,:)); %choose first 40 vectors and normalize
    
    eigenface = normr(eigenface);
    c1 = zeros(num_basis_vectors);
    c2 = zeros(num_basis_vectors);
    c3 = zeros(num_basis_vectors);
    
    for i = 1:num_basis_vectors
        c1(i) = dot(im_array2(index1,:), eigenface(i, :), 2);
        c2(i) = dot(im_array2(index2,:), eigenface(i, :), 2);
        c3(i) = c1(i) + (c2(i) - c1(i)) * 0.25;
    end
    
    face0 = zeros(1,16384);
    face1 = zeros(1,16384);
    face2 = zeros(1,16384);
    
    for i=1:num_basis_vectors
        face0(1,:) = face0(1,:) + c1(i).*eigenface(i,:);
        face1(1,:) = face1(1,:) + c2(i).*eigenface(i,:);
        face2(1,:) = face2(1,:) + c3(i).*eigenface(i,:);
    end
    
    face0 = mat2gray(face0);
    face1 = mat2gray(face1);
    morphed_face = mat2gray(face2);
    figure()
    imshow(reshape(morphed_face(1,:),[128,128]));
    
end
