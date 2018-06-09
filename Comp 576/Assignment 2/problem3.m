function [AS, AS_BAD, x_mag_diff, x_phase_diff, xy_mag_diff, xy_phase_diff, x_mag_diff_GOOD, x_phase_diff_GOOD, xy_mag_diff_GOOD, xy_phase_diff_GOOD] = problemThree(del_x, del_y, img1, img2)
    % Resize img1 -> IA
    [w,h,~] = size(img1);
    n_x = floor(w/128);
    n_y = floor(h/128);
    IA = zeros(128,128);
    
    for j = 1:128
        for k = 1:128
            IA(j,k) = img1(j*n_x, k*n_y);
        end
    end
    
    % Resize img2 -> IB
    [w,h,~] = size(img2);
    n_x = floor(w/128);
    n_y = floor(h/128);
    
    IB = zeros(128,128);
    
    for j = 1:128
        for k = 1:128
            IB(j,k) = img2(j*n_x, k*n_y);
        end
    end
    
    % Shift image
    % First only along x
    IA_shiftX = zeros(128,128);
    IA_shiftX_GOOD = zeros(128,128);
    
    for j = 1:128       % jth row (y)
        for k = 1:128   % kth col (x)
            x = k+del_x;
            if x < 1 || x > 128
                x = mod((x-1), 128)+1;
            end
            IA_shiftX(j,k) = IA(j, x);
            IA_shiftX_GOOD(j,x) = IA(j,k);
        end
    end
    
    % Next along both
    IA_shiftXY = zeros(128,128);
    IA_shiftXY_GOOD = zeros(128,128);
    
    for j = 1:128       % jth row (y)
        for k = 1:128   % kth col (x)
            x = k+del_x;
            y = j+del_y;
            if x < 1 || x > 128
                x = mod((x-1), 128)+1;
            end
            if y < 1 || y > 128
                y = mod((y-1), 128)+1;
            end
            IA_shiftXY(j,k) = IA(y, x);
            IA_shiftXY_GOOD(y,x) = IA(j,k);
        end
    end
    
    AS_BAD = IA_shiftXY;
    AS = IA_shiftXY_GOOD;
    
    % Calculate mag,phase for UNshifted
    [A_mag, A_phase] = AmpPhaseDFT(IA);
    
    % Calculate mag, phase for x-shifted
    [Ax_mag, Ax_phase] = AmpPhaseDFT(IA_shiftX);
    [Ax_mag_GOOD, Ax_phase_GOOD] = AmpPhaseDFT(IA_shiftX_GOOD);
    
    % Calculate mag, phase for x,y-shifted
    [Axy_mag, Axy_phase] = AmpPhaseDFT(IA_shiftXY);
    [Axy_mag_GOOD, Axy_phase_GOOD] = AmpPhaseDFT(IA_shiftXY_GOOD);
    
    % Return mag & phase difference matrices
    x_mag_diff      = abs(Ax_mag-A_mag);
    x_phase_diff    = abs(Ax_phase-A_phase);
    
    xy_mag_diff     = abs(Axy_mag-A_mag);
    xy_phase_diff     = abs(Axy_phase-A_phase);
    
    x_mag_diff_GOOD      = abs(Ax_mag_GOOD-A_mag);
    x_phase_diff_GOOD    = abs(Ax_phase_GOOD-A_phase);
    
    xy_mag_diff_GOOD     = abs(Axy_mag_GOOD-A_mag);
    xy_phase_diff_GOOD     = abs(Axy_phase_GOOD-A_phase);
    
end
