function [IA, IB, OA, OB] = problemOne(img1, img2) 
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
    
    % Calculate output of A -> OA
    [A_mag, A_phase] = AmpPhaseDFT(IA);
    OA = ReconfromAmpPhase(A_mag, A_phase);
    
    % Calculate output of B -> OB
    [B_mag, B_phase] = AmpPhaseDFT(IB);
    OB = ReconfromAmpPhase(B_mag, B_phase);
    
end
