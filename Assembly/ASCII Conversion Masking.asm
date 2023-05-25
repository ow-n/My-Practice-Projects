# ASCII to Integer Conversion - Masking / Procedures / Matrix

.globl main

.data
VString:		.space 12 	# 4 Integers, 2*4 + 3space + 1null = 12
ask_matrix:		.asciiz 	"Input 4x4 Matrix"
ask_row:		.asciiz		"Row "
colon:			.asciiz		": "
arrow:			.asciiz		" -> "
print_sum:		.asciiz		"Sum: "
parity:			.asciiz		"Parity: "
matrix_equals:	.asciiz		"Matrix Equals: "
				.align 4	# ensures V is aligned to a word boundary
M:				.space 64	# 4x4 Matrix with 64 bytes of memory
sum:			.space 4	# Sum of Matrix
MTest:			.asciiz		"1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4"

.text
main:
	jal get_VString			# Ask for 12345678 code, Format: 01 02 03 04 05 06 07 08
	jal make_matrix_M		# Make 4x4 Matrix M
	jal get_sum				# Prints Sum
	jal get_parity			# Prints Even or Odd
	jal exit_program

# ------------------------- Subroutines ------------------------- #
get_VString:
	li $v0, 4					# [Service]: Print String
	la $a0, ask_matrix			# load address of ask_Matrix
	syscall
	addiu $sp, $sp, -4  		# allocate space on the stack
	sw $ra, 0($sp)      		# store the return address to the stack
	jal new_line
    lw $ra, 0($sp)      		# load the return address from the stack
	addiu $sp, $sp, 4   		# deallocate the stack space
	jr $ra						# Return from subroutine (using $ra)

make_matrix_M:
    move $s0, $ra           	# save the return address in $s0
    li $t0, 4               	# store constant '4' for loop conditional
    beq $s3, $t0, exit_loop 	# if i == 4, then exit
    jal print_row_number    	# Print "Row #: "
    # Read input code, format; 01 02 03 04
    li $v0, 8               	# [Service]: Read String
    la $a0, VString         	# load address of input buffer into $a0 (input buffer = temp storage)
    li $a1, 12              	# set maximum number of characters to be read
    syscall
    li $s1,0                	# reset s1 (col) to 0
    jal print_arrow         	# Print " -> "
    sll $s5, $s3, 4				# offset by column($s3) by 16 / 2^4
    jal string_to_integer   	# Convert VString into integer + Stored in Matrix
    jal reset_i_j           	# Resets index of string and integer
    
    jal new_line
    # Store updated array index
    addi $s3, $s3, 1        	# loop index + 1
    move $ra, $s0           	# restore the return address from $s0
    j make_matrix_M        		# Loop: jump to string_to_integer

	string_to_integer:
    	move $s4, $ra           	# save the return address in $s4
    	li $t0, 12              	# store constant '12' for loop conditional
    	beq $s1, $t0, exit_loop 	# if i == 12, then exit
    	la $t1, VString         	# load address of input buffer into $t1 (string_array index)
    	# Get position of string_array
    	add $t1, $t1, $s1        	# increment string_array by i(position of string array)
    	lb $t2, 0($t1)          	# load first byte from string_array
    	lb $t3, 1($t1)          	# load second byte from string_array (offset 1 byte)
    	# Convert ASCII to Integer (Masking Technique) (I did arithmetic last lab)
    	andi $t2, $t2, 0x0F     	# mask first digit with 0x0F to get the integer value
    	andi $t3, $t3, 0x0F     	# mask second digit with 0x0F to get the integer value
    	# Combine the two digits into a single integer + Print (2 shifts,sll,add)
    	sll $t4, $t2, 2         	# multiply the first digit($t2) by 4 (shift left by 2)
    	add $t2, $t2, $t4       	# add the two results together (X + 4X)
		sll $t2, $t2, 1        	 	# multiply the result by 2 (shift left by 1) to get 10X
		add $t8, $t2, $t3			# add the second digit to the first (now multiplied by 10)
		li $v0, 1					# [Service]: Print Integer in $a0
		move $a0, $t8				# copy t8 integer to a0
		syscall
		jal print_space
		# Store integer in M
		la $t5, M                	# load address of integer matrix M into $t5
		add $t5, $t5, $s5        	# add row offset (in $s5) to the address of M
		sll $t7, $s1, 2          	# multiply column number by 4
		add $t5, $t5, $s2        	# add column number to offset to get next byte
		sw $t8, 0($t5)           	# store new integer($a0) into M($t5)
		# Store updated array index
		addi $s1, $s1, 3       		# i (input index) + 2 (2 digits + ' ')
		addi $s2, $s2, 4			# j (int array) + 4 (next byte)
		move $ra, $s4				# restore the return address from $s0
		j string_to_integer			# Loop: jump to string_to_integer

	reset_i_j:
		addi $s1, $zero, 0		# reset i to 0 ($s1)
		addi $s2, $zero, 0		# reset j to 0 ($s2)
		sw $s1, 0($sp)			# store i on stack (index of input string(source))
		sw $s2, 4($sp)			# store j on stack (index of integer array(target))
		jr $ra					# Return

	print_row_number: 			# Print "Row #: "
		li $v0, 4				# [Service]: Print String
		la $a0, ask_row			# load address of ask_Row
		syscall
		li $v0, 1				# [Service]: Print Integer
		addi $a0, $s3, 1
		syscall
		li $v0, 4				# [Service]: Print String
		la $a0, colon			# load address of colon
		syscall
		jr $ra

	print_arrow:
		li $v0, 4				# [Service]: Print String
		la $a0, arrow			# load address of arrow
		syscall
		jr $ra

# ======================================================================= #

get_sum:
	addiu $sp, $sp, -4  	# allocate space on the stack
	sw $ra, 0($sp)      	# store the return address to the stack
	la $t0, M          		# load the address of the array into $t0
	li $v0, 4				# syscall code for printing an word
	la $a0, print_sum		# move the matrix to $a0
	syscall					# print the matrix
	addi $s1, $zero, 0		# reset i to 0 ($s1)
	li $t3, 0          		# initialize sum to 0
	jal calculate_sum
	
	la $t5, sum				# load address of sum into $t5
	sw $t3, 0($t5)			# store the sum
	li $v0, 1              	# syscall code for printing an integer
	move $a0, $t3          	# move the sum to $a0
	syscall					# print the sum
	jal new_line
	lw $ra, 0($sp)      	# load the return address from the stack
	addiu $sp, $sp, 4   	# deallocate the stack space
	jr $ra

	calculate_sum:
		li $t1, 16           	# load the size of the array into $t1
    	beq $s1, $t1, exit_loop # if the loop counter is equal to the size, exit the loop
    	lw $t4, 0($t0)          # load the current array element into $t4
 #   		li $v0, 1               # [Service]: Print Integer in $a0
 #   		move $a0, $t4           # move the value of $t4 to $a0				# debugging eaah element
 # 			syscall                 # Execute the print integer syscall
 #   		li $v0, 4               # [Service]: Print String
 #   		la $a0, arrow           # load address of space (or any other separator string)
 #   		syscall                 # Execute the print string syscall
   		add $t3, $t3, $t4       # add the current element to the sum
   		addi $t0, $t0, 4        # increment the array address by 4 bytes (since it's a word array)
   		addi $s1, $s1, 1        # increment the loop counter i
		j calculate_sum			# jump back to the start of the loop

# ======================================================================= #

get_parity:
	addiu $sp, $sp, -4  	# allocate space on the stack
	sw $ra, 0($sp)      	# store the return address to the stack
	li $v0, 4				# [Service]: Print String
	la $a0, parity			# "Party: "
	syscall
    lw $t0, sum            	# load the sum into $t0
	li $v0, 1              	# [Service]: Print Integer
    andi $t1, $t0, 1       	# bitwise AND between the sum and 1 to check the least significant bit
    # Even:
    beq $t1, $zero, even   	# if the result is 0, jump to even
    # Odd:
    li $a0, 1              	# load 1 (for odd) into $a0
	j print_result

		even:
    		li $a0, 0              # load 0 (for even) into $a0

		print_result:
    		syscall                # print the result (0 or 1)
    		lw $ra, 0($sp)      	# load the return address from the stack
			addiu $sp, $sp, 4   	# deallocate the stack space
    		jr $ra                 # return

exit_program:
	li $v0, 10				# [System]: Exit
	syscall

	print_space:
		li $v0, 11			# [Service]: Print Character
		li $a0, 32			# load ASCII value for space into $a0
		syscall
		jr $ra

	new_line:
		li $v0, 11			# [System]: Print Character
		li $a0, 10			# Load ASCII code for newline character
		syscall				# Print newline
		jr $ra				# Return from subroutine (using $ra)

	exit_loop:
		jr $ra				# Return from subroutine (using $ra)


# -------------------------- Output One -------------------------- #
# Input 4x4 Matrix
# Row 1: 01 02 03 04 -> 1 2 3 4 
# Row 2: 10 20 30 40 -> 10 20 30 40 
# Row 3: 01 20 03 40 -> 1 20 3 40 
# Row 4: 11122233344 -> 11 22 33 44 
# Sum: 284
# Parity: 0

# -------------------------- Output Two -------------------------- #
# Input 4x4 Matrix
# Row 1: 10 20 30 40 -> 10 20 30 40 
# Row 2: 10 20 30 40 -> 10 20 30 40 
# Row 3:             -> 0 0 0 0 
# Row 4:     1       -> 0 1 0 0 
# Sum: 201
# Parity: 1
