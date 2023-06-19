# ASCII to Integer Conversion - Arithmetic / Vector Format / Place Value

.globl main

.data
ask_VString:	.asciiz 	"Input V: "
VString:		.space 24 	# ID=8 spaces, 2*8 + 7space + 1null = 24
				.align 4	# ensures V is aligned to a word boundary
V: 				.space 32 	# Integer Array - 32 bytes reserved for 8 ints
VPrime: 		.space 32
VCheck: 		.space 32
ask_VPrime: 	.asciiz 	"Input V prime: "
check_result: 	.asciiz 	"Check Result: "

.text
main:
	jal get_VString			# Ask for 12345678 code, Format: 01 02 03 04 05 06 07 08
	jal initialize_i_j		# Create 2 variables(on stack) to store source and target index
	jal new_line

	jal string_to_integer	# Converts string array to integer array
	jal new_line
	jal new_line 

	jal initialize_i_j		# Resets variables i & j
	jal get_VPrime			# Asks for VPrime (to subtract from V)
	jal new_line 
	jal make_VPrime			# Makes VPrime for user input, Format: 1 2 3 4 5 6 7 8 
	jal new_line

	jal initialize_i_j		# Resets variables i & j
	jal subtract_array		# Subtracts VPrime from V
	jal print_VCheck		# Prints VCheck (tests if subtraction worked)

	jal exit_program

# ------------------------- Subroutines ------------------------- #
get_VString:
	li $v0, 4				# [Service]: Print String
	la $a0, ask_VString		# load address of ask_VString
	syscall
	li $v0, 8				# [Service]: Read String
	la $a0, VString			# load address of input buffer into $a0 (input buffer = temp storage)
	li $a1, 24				# set maximum number of characters to be read
	syscall
	jr $ra					# Return from subroutine (using $ra)

initialize_i_j:
	addi $s0, $zero, 0		# initialize i to 0
	addi $s1, $zero, 0		# initialize j to 0
	sw $s0, 0($sp)			# store i on stack (index of input string(source))
	sw $s1, 4($sp)			# store j on stack (index of integer array(target))
	jr $ra					# Return

string_to_integer:
	li $t0, 24				# store constant '24' for loop conditional
	beq $s0, $t0, exit_loop	# if i == 24, then exit
	lw $s0, 0($sp)			# load i from stack (position of string array)
	lw $s1, 4($sp)			# load j from stack (position of integer araay)
	la $t1, VString			# load address of input buffer into $t1 (string_array index)
	# Get position of string_array
	add $t1, $t1, $s0		# increment string_array by i(position of string array)
	lb $t2, 0($t1)     		# load first byte from string_array
	lb $t3, 1($t1)			# load second byte from string_array (offset 1 byte)
	# Convert ASCII to Integer (Arithmetic Technique)
	addi $t2, $t2, -48		# convert first digit from ASCII to integer
	addi $t3, $t3, -48		# convert second digit from ASCII to integer
	# Combine the two digits into a single integer + Print (2 shifts,sll,add)
	sll $t4, $t2, 3			# multiply the first digit($t2) by 8 (shift left by 3)
	sll $t2, $t2, 1			# multiply the first digit($t2) by 2 (shift left by 1)
	add $t2, $t2, $t4		# add the two results together (8X + 2X)
	add $a0, $t2, $t3		# add the second digit to the first (now multiplied by 10)
	li $v0, 1				# [Service]: Print Integer in $a0
	syscall
	# Store integer in V
	la $t5, V				# load address of integer array V into $t4
	add $t5, $t5, $s1		# prepare next V index by adding j(x*4)
	sw $a0, ($t5)			# store new integer($a0) into V($t4)
	# Store updated array index
	addi $s0, $s0, 3       	# i (input index) + 2 (2 digits + ' ')
	addi $s1, $s1, 4		# j (int array) + 4 (next byte)
	sw $s0, 0($sp)			# store i on stack
	sw $s1, 4($sp)      	# store j on stack
	j string_to_integer		# Loop: jump to string_to_integer

get_VPrime:
	li $v0, 4				# [Service]: Print string
	la $a0, ask_VPrime		# load address of ask_VPrime
	syscall
	jr $ra					# Return

make_VPrime:
	li $t0, 8				# store constant '8' for loop conditional
	beq $s0, $t0, exit_loop	# Exit Loop: branch to sub_array if i (V index) == 8
	# Get and store next VPrime integer
	li $v0, 5				# [Service]: Read Integer into $v0
	syscall
	la $t1, VPrime			# load address of VPrime into $t1
	add $t1, $t1, $s1		# offset VPrime index by j (VPrime index)
	sw $v0, 0($t1)			# store read integer n in VPrime
	# Update array index
	addi $s0, $s0, 1		# increment i by 1 (1->2345..)
	addi $s1, $s1, 4		# increment j by 4 (next byte)
	j make_VPrime			# Loop

subtract_array:
	li $t0, 8				# store constant '8' for loop conditional
	beq $s0, $t0, exit_loop	# Exit Loop: branch to print_VCheck if i (V index) == 8
	lw $s0, 0($sp)			# load i from stack (loop counter, exit after 8 digit code)
	lw $s1, 4($sp)			# load j from stack (array index)
	# Load V, VPrime, VCheck
	la $t1, V				# load address of V into $t1
	la $t2, VPrime			# load address of VPrime into $t2
	la $t3, VCheck			# load address of VCheck into $t3
	# Offset V, Vprime, VCheck by j
	add $t1, $t1, $s1		# add offset to $t1
	add $t2, $t2, $s1		# add offset to $t2
	add $t3, $t3, $s1		# add offset to $t3
	# Load V, VPrime
	lw $t5, ($t1)			# load integer from location in V
	lw $t6, ($t2)			# load integer from location in VPrime
	# Subtract and Store
	sub $t5, $t5, $t6		# subtract t6 from t5
	sw $t5, ($t3)			# store result in location in VCheck ($t3)
	# Update array index
	addi $s0, $s0, 1		# increment i by 1
	addi $s1, $s1, 4		# increment j by 4
	sw $s0, 0($sp)			# store updated i onto stack
	sw $s1, 4($sp)			# store updated j onto stack
	j subtract_array		# Loop

print_VCheck:
	li $v0, 4				# [Service]: Print String
	la $a0, check_result	# load address of string to be printed
	syscall
	addi $s0, $zero, 0		# set $s0 to 0
	addi $s1, $zero, 0		# set $s1 to 0
	sw $s0, 0($sp)			# store 0 at address pointed to by $sp
	sw $s1, 4($sp)			# Store 0 at address pointed to by $sp + 4
	j print_VCheck_integers	# Jump to print

print_VCheck_integers:
	li $t0, 8				# store constant '8' for loop conditional
	beq $s0, $t0, exit_loop	# Exit Loop: if i == 8, jump to exit_loop then back to $ra
	lw $s0, 0($sp)			# load i from stack (loop counter, exit after 8 digit code)
	lw $s1, 4($sp)      	# load j from stack (position of integer araay)
	la $t1, VCheck			# load address of VCheck
	add $t1, $t1, $s1		# offset VCheck by j (index)
	lw $a0, ($t1)			# load integer at address
	li $v0, 1				# [System]: Print Integer
	syscall					
	addi $s0, $s0, 1		# increment i
	addi $s1, $s1, 4		# increment j
	sw $s0, 0($sp)			# store i on stack
	sw $s1, 4($sp)			# store j on stack
	j print_VCheck_integers	# Loop

exit_program:
	addi $sp, $sp, 8		# restore stack pointer
	li $v0, 10				# [System]: Exit
	syscall

new_line:
	li $v0, 11				# [System]: Print Character
	li $a0, 10				# Load ASCII code for newline character
	syscall					# Print newline
	jr $ra					# Return from subroutine (using $ra)
	
exit_loop:
	jr $ra					# Return from subroutine (using $ra)


# -------------------------- Output -------------------------- #
# Input V: 01 02 03 44 05 06 07 88
# 1234456788
# 
# Input V prime: 
# 1
# 2
# 3
# 44
# 5
# 6
# 7
# 88
# 
# Check Result: 00000000
