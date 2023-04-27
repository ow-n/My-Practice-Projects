import re

# Define the mappings from old symbols to new symbols
symbol_mappings = {
    'Î': '∈',
    '"': '∀',
    'é': '⌈',
    'ù': '⌉',
    '@': '≅'
}

# Open the input file and read its contents
with open('input_file.txt', 'r', encoding='utf-8') as f:
    input_text = f.read()

# Replace each symbol in the input text using regular expressions
for old_symbol, new_symbol in symbol_mappings.items():
    pattern = re.compile(re.escape(old_symbol))
    input_text = pattern.sub(new_symbol, input_text)

# Write the modified text to an output file
with open('output_file.txt', 'w', encoding='utf-8') as f:
    f.write(input_text)
