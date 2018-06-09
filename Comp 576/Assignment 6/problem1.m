function [z_slice, z_rotated_slice, q, R, d, A] = problemOne(z_index, c_x, c_y, c_z, r_x, r_y, r_z, theta, axis)
    size_z = 16;
    size_y = 16;
    size_x = 16;
    
    I = zeros(size_x,size_y,size_z);
    
    c_vox = [c_x, c_y, c_z];
    
    radii = [r_x,r_y,r_z];
    
%     f = inline('(x-c_x)^2/(r_x^2) + (y-c_y)^2/(r_y^2) + (z-c_z)^2/(r_z^2) - 1');
    
    for i = 1:size_x
        for j = 1:size_y
            for k = 1:size_z
                % Unit voxel at (i,j,k)
                contained_points = 0;
                for x = [i-0.5, i+0.5]
                    for y = [j-0.5, j+0.5]
                        for z = [k-0.5, k+0.5]
                            %val = f(x, c_vox(1), radii(1), y, c_vox(2), radii(2), z, c_vox(3), radii(3));
                            val = f(c_vox(1), c_vox(2), c_vox(3), radii(1), radii(2), radii(3), x, y, z);
                            if val <= 0
                                contained_points = contained_points + 1;
                            end
                        end
                    end
                end
                I(i, j, k) = contained_points;
            end
        end
    end
    
    % Define quarternion
    %q = [0.7071203316249954, 0.0, 0.7071203316249954, 0.0];
    %q_prime = [0.7071203316249954, 0.0, -0.7071203316249954, 0.0];
%     theta = pi/4;
    q = [cos(theta/2) sin(theta/2) 0 0];
    qw = q(1);
    qx = q(2);
    qy = q(3);
    qz = q(4);
    R = [1-2*(qy^2+2*qz^2), 2*(qx*qy+qz*qw), 2*(qx*qz+qy*qw); 
        2*(qx*qy + qz*qw), 1 - 2*(qx^2 + qz^2),	2*(qy*qz - qx*qw); 
        2*(qx*qz - qy*qw), 2*(qy*qz + qx*qw), 1 - 2*(qx^2 + qy^2)];

    %R = quat2rotm(q);
    %R = [1-2*(qy^2+2*qz^2), 2*(qx*qy+qz*qw), 2*(qx*qz+qy*qw); 
    %    2*(qx*qy + qz*qw), 1 - 2*(qx^2 + qz^2),	2*(qy*qz - qx*qw); 
    %    2*(qx*qz - qy*qw), 2*(qy*qz + qx*qw), 1 - 2*(qx^2 + qy^2)];
    
    % Interpolate image I into J
    %J = interp3(I);
    
    I_prime = zeros(size_x, size_y, size_z);
    
    for i = 1:size_x
        for j = 1:size_y
            for k = 1:size_z
                v = [i j k];
                % Translate to origin
                v_origin = (v-[1 1 1]) - (c_vox - [1 1 1]);
                % Rotate
                %R = eye(3);
                v_rotate = R * v_origin';
                %v_rotate = [0; v_origin'];
                % Translate back to center
                %v_prime = v_rotate' + c_vox;
                v_prime = (v_rotate'+[1 1 1]) + (c_vox - [1 1 1]);
                
                % Set values in I_prime
                I_prime(i, j, k) = interp3(I, min(max(v_prime(1), 1), size_x), min(max(v_prime(2), 1), size_y), min(max(v_prime(3), 1), size_z));
                
                if isnan(I_prime(i,j,k))
                    disp([i j k])
                end
                
                %I_prime(v_prime(1), v_prime(2), v_prime(3)) = I(i, j, k);
            end
        end
    end
    sumx = 0;
    sumy = 0;
    sumz = 0;
    
    dx = 0;
    dy = 0;
    dz = 0;
    
    for i = 1:size_x
        for j = 1:size_y
            for k = 1:size_z
                dx = dx + I(i,j,k)*i;
                dy = dy + I(i,j,k)*j;
                dz = dz + I(i,j,k)*k;
                
                sumx = sumx + I(i,j,k);
                sumy = sumy + I(i,j,k);
                sumz = sumz + I(i,j,k);
            end
        end
    end
    
    
    dx = dx/sumx;
    dy = dy/sumy;
    dz = dz/sumz;
    
    d = [dx,dy,dz];
    
    g = zeros(3,3);
    
    for i = 1:size_x
        for j = 1:size_y
            for k = 1:size_z
                g(1,1) = g(1,1) + I_prime(i,j,k)*(i-dx)*(i-dx);
                g(1,2) = g(1,2) + I_prime(i,j,k)*(i-dx)*(j-dy);
                g(1,3) = g(1,3) + I_prime(i,j,k)*(i-dx)*(k-dz);
                
                g(2,1) = g(2,1) + I_prime(i,j,k)*(j-dy)*(i-dx);
                g(2,2) = g(2,2) + I_prime(i,j,k)*(j-dy)*(j-dy);
                g(2,3) = g(2,3) + I_prime(i,j,k)*(j-dy)*(k-dz);
                
                g(3,1) = g(3,1) + I_prime(i,j,k)*(k-dz)*(i-dx);
                g(3,2) = g(3,2) + I_prime(i,j,k)*(k-dz)*(j-dy);
                g(3,3) = g(3,3) + I_prime(i,j,k)*(k-dz)*(k-dz);
            end
        end
    end
    A = g;
    z_slice = I(:,:,z_index);
    z_rotated_slice = I_prime(:,:,z_index);
    
     function val = f(c_x,c_y,c_z,r_x,r_y,r_z,x,y,z)
         val = (x-c_x)^2/(r_x^2) + (y-c_y)^2/(r_y^2) + (z-c_z)^2/(r_z^2) - 1;
     end
end
