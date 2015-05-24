S1 = [-1;-1];     V1 = [1;1];
S2 = [-1;0];     V2 = [1;0];

% ---

if (V1(1) == 0 && V2(2) == 0) || (V2(1) == 0 && V2(2) == 0)
    if V2(1) ~= 0 || V2(2) ~= 0        
        VTMP = V2;
        V2 = V1;
        V1 = VTMP;
        
        VS = S2;
        S2 = S1;
        S1 = VS;
    end
    
    t1 = (S2(1)-S1(1))/V1(1);
    t2 = (S2(2)-S1(2))/V1(2);
    
    if ~isfinite(t1)
        t1 = 0;
    end
    
    if ~isfinite(t2)
        t2 = 0;
    end
    
    T = [t1;t2];
else
    A = [[V1(1) -V2(1)]; [V1(2) -V2(2)]];
    S = S2 - S1;

    if any(S)
        T = A\S;
    else    
        T = [0;0];
    end
end

if T(1) == T(2) && ~isnan(T(1)) && isfinite(T(1))
    disp('Intersection with time found.');
end

I1 = S1 + T(1)*V1;
I2 = S2 + T(2)*V2;

