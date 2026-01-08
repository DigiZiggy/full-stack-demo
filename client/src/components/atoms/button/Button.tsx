import React from 'react';
import './Button.less';

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
    variant?: 'primary' | 'secondary' | 'danger' | 'outline';
    size?: 'small' | 'medium' | 'large';
    isLoading?: boolean;
}

export const Button: React.FC<ButtonProps> = ({
                                                  children,
                                                  variant = 'primary',
                                                  size = 'medium',
                                                  isLoading = false,
                                                  className = '', // Allow passing extra custom classes
                                                  disabled,
                                                  ...props // Spread remaining props (like onClick) to the button element
                                              }) => {
    const btnClasses = [
        'btn',
        `btn-${variant}`,
        `btn-${size}`,
        className
    ].join(' ').trim();

    return (
        <button
            className={btnClasses}
            disabled={disabled || isLoading}
            {...props}
        >
            {isLoading ? <span className="btn-loader">...</span> : children}
        </button>
    );
};