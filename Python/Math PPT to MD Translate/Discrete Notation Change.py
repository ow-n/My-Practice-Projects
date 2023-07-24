import re

# Found solution Google Slides correctly decodes the unicode and
# the characters can be correctly copied from there... no need for this anymore

# Program that fixes the encoding issue of math notation in pdfs

# Define the mappings from old symbols to new symbols
symbol_mappings = {
    '"': '∀', 
    '$': '∃',  
    'Î': '∈',
    '\'': '∋',
    'é': '⌈',
    'ù': '⌉',
    '@': '≅',
    '®': '→',
    'Í': '⊆',
    '³': '≥',
    '´': '×',
    '\\': '∴',
}

# Subscript Mapping
subscript_mappings = {
    '0': '₀',
    '1': '₁',
    '2': '₂',
    '3': '₃',
    '4': '₄',
    '5': '₅',
    '6': '₆',
    '7': '₇',
    '8': '₈',
    '9': '₉'
}

# Superscript Mapping
superscript_mappings = {
    '0': '⁰',
    '1': '¹',
    '2': '²',
    '3': '³',
    '4': '⁴',
    '5': '⁵',
    '6': '⁶',
    '7': '⁷',
    '8': '⁸',
    '9': '⁹'
}

# Function to handle matched subscripted numbers
def handle_subscript(match):
    return ''.join(subscript_mappings.get(digit, digit) for digit in match.group())

# Function to handle matched superscripted numbers
def handle_superscript(match):
    return ''.join(superscript_mappings.get(digit, digit) for digit in match.group())

# Regex to match digits that immediately follow a non-digit character
subscript_pattern = re.compile(r'(?<=\D)\d+') 

# Open the input file and read its contents line by line
with open('input_file.txt', 'r', encoding='utf-8') as f:
    lines = f.readlines()

output_lines = []
last_char_was_digit = False

for line in lines:
    # Apply superscript if last line ended with a digit
    if last_char_was_digit:
        first_digit = re.search(r'\d', line)
        if first_digit is not None:
            line = line[:first_digit.start()] + handle_superscript(first_digit) + line[first_digit.end():]

    # Apply subscript and other symbol replacements
    line = subscript_pattern.sub(handle_subscript, line)
    for old_symbol, new_symbol in symbol_mappings.items():
        pattern = re.compile(re.escape(old_symbol))
        line = pattern.sub(new_symbol, line)

    # Check if the last character of this line is a digit for the next iteration
    last_char_was_digit = line.strip() and line.strip()[-1].isdigit()

    # Append the modified line to the output
    output_lines.append(line)

# Write the modified lines to an output file
with open('output_file.txt', 'w', encoding='utf-8') as f:
    f.writelines(output_lines)
