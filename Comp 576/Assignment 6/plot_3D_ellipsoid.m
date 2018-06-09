function [x_ellipsoid, y_ellipsoid, z_ellipsoid] = plot_3D_ellipsoid(ellipsoid_center, ellipsoid_semi_axis_long,...
    ellipsoid_semi_axis_medium, ellipsoid_semi_axis_short)
%plot_ellipsoid plots an ellipsoid with an arbitrary center and arbitrary semi
%axes vectors. (Semi axess must be orthogonal)
% plot_ellipsoid receives the center of the ellipsoid, and the vectors that
% serve as the semiaxes of the ellipsoid and plots the ellipsoid, its axes
% and its three cross sections that pass through the center and are
% perpendicular to the axes.

% written by: Farangis Raam
% January 2014

% example: 
%{
[x_ellipsoid, y_ellipsoid, z_ellipsoid] = plot_3D_ellipsoid([1 1 1], [1 2 1], [1 -1 1], [1 0 -1])

plots an ellipsoid with its center at: 
ellipsoid_center = [x_c,y_c,z_c] = [1 1 1]
and its three semi axes as:
ellipsoid_semi_axis_long = [1 2 1]
ellipsoid_semi_axis_medium = [1 -1 1]
ellipsoid_semi_axis_short = [1 0 -1]

[x_ellipsoid, y_ellipsoid, z_ellipsoid] are the coordinates of the point
located on the 3D ellipsoid
%}

%% semi_axes lengths

l_semi_1 = norm(ellipsoid_semi_axis_long);
l_semi_2 = norm(ellipsoid_semi_axis_medium);
l_semi_3 = norm(ellipsoid_semi_axis_short);

%% generate the upright ellipsod
% The upright ellipsoid's semi_axis are taken to be parallel to x,y,z and their
% length equal to the lengths of the semi axes of the 3D ellipsoid 

semi_axis_1 = [1 0 0]*l_semi_1;
semi_axis_2 = [0 1 0]*l_semi_2;
semi_axis_3 = [0 0 1]*l_semi_3;

% The point on upright ellipsod with its center on [0 0 0] 

[x, y, z] = ellipsoid(0,0,0,l_semi_1,l_semi_2,l_semi_3,50);

[s_r, s_c] = size(x);

%% The rotation matrix that takes the upright axes to the 3D ellipsoid axes

% R X = E
% R = E / X;

X = [semi_axis_1' semi_axis_2' semi_axis_3'];
E = [ellipsoid_semi_axis_long' ellipsoid_semi_axis_medium' ellipsoid_semi_axis_short'];

R = E/X; % R is basically the same as E'

%% Rotate the points on upright ellipsoid with R

Rotated_xyz = R * [x(:) y(:) z(:)]';
x_r = Rotated_xyz(1,:);
y_r = Rotated_xyz(2,:);
z_r = Rotated_xyz(3,:);

%% move the center of the ellipsoid to the ellipsoid center

x_ellipsoid = x_r + ellipsoid_center(1); 
y_ellipsoid = y_r + ellipsoid_center(2); 
z_ellipsoid = z_r + ellipsoid_center(3); 

%% reshape to matrices

x_r = reshape(x_ellipsoid, s_r,s_c);
y_r = reshape(y_ellipsoid, s_r,s_c);
z_r = reshape(z_ellipsoid, s_r,s_c);

%% plot the rotated ellipsoid

surf(x_r,y_r,z_r, 'FaceColor', 'red', 'EdgeColor', 'none', 'FaceLighting', 'Phong');
% shading interp
axis equal
alpha(0.2)
% camlight left
% lightangle(45,30);
% set(gcf,'Renderer','zbuffer'); 
% lighting phong

%% plot the 3D ellipsoid's semi axes

hold on

x_r_l = [0 ellipsoid_semi_axis_long(1)]+ ellipsoid_center(1);
y_r_l = [0 ellipsoid_semi_axis_long(2)]+ ellipsoid_center(2);
z_r_l = [0 ellipsoid_semi_axis_long(3)]+ ellipsoid_center(3);

plot3(x_r_l, y_r_l, z_r_l, 'k', 'LineWidth', 2)

x_r_m = [0 ellipsoid_semi_axis_medium(1)]+ ellipsoid_center(1);
y_r_m = [0 ellipsoid_semi_axis_medium(2)]+ ellipsoid_center(2);
z_r_m = [0 ellipsoid_semi_axis_medium(3)]+ ellipsoid_center(3);

plot3(x_r_m, y_r_m, z_r_m, 'k', 'LineWidth', 2)

x_r_s = [0 ellipsoid_semi_axis_short(1)]+ ellipsoid_center(1);
y_r_s = [0 ellipsoid_semi_axis_short(2)]+ ellipsoid_center(2);
z_r_s = [0 ellipsoid_semi_axis_short(3)]+ ellipsoid_center(3);

plot3(x_r_s, y_r_s, z_r_s, 'k', 'LineWidth', 2)

%% plot the ellipses of cross sections

t = 0:pi/60:2*pi;
x_e_12 = l_semi_1*cos(t);
y_e_12 = l_semi_2*sin(t);
z_e_12 = zeros(size(t));

% Rotate the ellipse

Rotated_xyz = R * [x_e_12; y_e_12; z_e_12];

x_r_e12 = Rotated_xyz(1,:)+ ellipsoid_center(1);
y_r_e12 = Rotated_xyz(2,:)+ ellipsoid_center(2);
z_r_e12 = Rotated_xyz(3,:)+ ellipsoid_center(3);

plot3(x_r_e12, y_r_e12, z_r_e12, 'LineWidth', 2)
hold on

%% plot the ellipse of cross sections

x_e_13 = l_semi_1*cos(t);
y_e_13 = zeros(size(t));
z_e_13 = l_semi_3*sin(t);

Rotated_xyz = R * [x_e_13; y_e_13; z_e_13];

x_r_e13 = Rotated_xyz(1,:)+ ellipsoid_center(1);
y_r_e13 = Rotated_xyz(2,:)+ ellipsoid_center(2);
z_r_e13 = Rotated_xyz(3,:)+ ellipsoid_center(3);

plot3(x_r_e13, y_r_e13, z_r_e13, 'LineWidth', 2)


%% plot the ellipse of cross sections

x_e_23 = zeros(size(t));
y_e_23 = l_semi_2*cos(t);
z_e_23 = l_semi_3*sin(t);

Rotated_xyz = R * [x_e_23; y_e_23; z_e_23];

x_r_e23 = Rotated_xyz(1,:)+ ellipsoid_center(1);
y_r_e23 = Rotated_xyz(2,:)+ ellipsoid_center(2);
z_r_e23 = Rotated_xyz(3,:)+ ellipsoid_center(3);


plot3(x_r_e23, y_r_e23, z_r_e23, 'LineWidth', 2)

view(40,12);
rotate3d on;

end
